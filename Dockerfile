##
## Build stage
##
#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src "$PWD":/src/main/java
#COPY pom.xml "$PWD":/
#RUN mvn -f "$PWD":/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY /target/itunes-microservice-1.1.0-SNAPSHOT.jar itunes-microservice.jar
EXPOSE 8035
CMD java -jar -Dspring.profiles.active=local itunes-microservice.jar
