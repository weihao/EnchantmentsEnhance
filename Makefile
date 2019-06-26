########################################
help:
	@echo "You have the following options when using this Makefile:"
	@echo
	@echo "$(OK_COLOR)Development related:"
	@echo "$(OK_COLOR)  build $(NO_COLOR)- builds EE"
	@echo "$(OK_COLOR)  docker-build $(NO_COLOR)- builds EE using Docker"


########################################
DOCKER_IMAGE := 25/enchantmentsenhance
VERSION := $(shell mvn -q -Dexec.executable="echo" -Dexec.args='$${project.version}' --non-recursive exec:exec)

NO_COLOR=\x1b[0m
OK_COLOR=\x1b[32;01m
ERROR_COLOR=\x1b[31;01m
WARN_COLOR=\x1b[33;01m


########################################

build:
	mvn clean package

docker-build:
	@printf "$(OK_COLOR)Building EnchantmentsEnhance ${VERSION} jar...$(NO_COLOR)\n"
	@printf "$(OK_COLOR)Launching Docker...$(NO_COLOR)\n"
	docker build -t ${DOCKER_IMAGE}:${VERSION} ./
	@echo "$(OK_COLOR)Done!$(NO_COLOR)"
