FROM openjdk:8-jdk-alpine3.7 AS builder
RUN java -version

COPY . /usr/src/enchantmentsenhance/
WORKDIR /usr/src/enchantmentsenhance/
RUN apk --no-cache add maven && mvn --version
RUN mvn package
