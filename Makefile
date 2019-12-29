########################################
help:
	@echo "You have the following options when using this Makefile:"
	@echo
	@echo "$(OK_COLOR)Development related:"
	@echo "$(OK_COLOR)  b $(NO_COLOR)- builds EE"
	@echo "$(OK_COLOR)  docker-build $(NO_COLOR)- builds EE using Docker"


########################################
DOCKER_IMAGE := 25/enchantmentsenhance
RELEASE_VERSION := $(shell mvn -q -Dexec.executable="echo" -Dexec.args='$${project.version}' --non-recursive exec:exec)
NEXT_VERSION  := $(shell echo $(RELEASE_VERSION) | perl -pe 's{^(([0-9]\.)+)?([0-9]+)$$}{$$1 . ($$3 + 1)}e')

NO_COLOR=\x1b[0m
OK_COLOR=\x1b[32;01m
ERROR_COLOR=\x1b[31;01m
WARN_COLOR=\x1b[33;01m


########################################

b:
	mvn install clean package

docker-build:
	@printf "$(OK_COLOR)Building EnchantmentsEnhance ${RELEASE_VERSION} jar...$(NO_COLOR)\n"
	@printf "$(OK_COLOR)Launching Docker...$(NO_COLOR)\n"
	docker build -t ${DOCKER_IMAGE}:${RELEASE_VERSION} ./
	@printf "$(OK_COLOR)Copying Artifact...$(NO_COLOR)\n"
	@mkdir -p ./out
	docker cp $$(docker create ${DOCKER_IMAGE}:${RELEASE_VERSION}):/usr/src/enchantmentsenhance/modules/EnchantmentsEnhance-Plugin/target/EnchantmentsEnhance-Plugin-${RELEASE_VERSION}.jar ./out/
	@printf "$(OK_COLOR)Done! Find the jar in the /out Directory$(NO_COLOR)\n"

set:
	mvn versions:set -DnewVersion=$(v)

bump:
	make set v=${NEXT_VERSION}

bump-push:
	make bump
	git add .
	git commit -m "Bump a version"
	git push

ci:
	mvn install clean package
	rm -rf build
	mkdir -p build
	cp modules/*/target/*.jar ./build
	rm ./build/original*