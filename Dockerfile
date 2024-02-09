#FROM maven:3.6.3-openjdk-17-slim as BUILDER
#LABEL authors="abduvohid"
#ARG version=0.0.1-SNAPSHOT
#WORKDIR /build/
#COPY pom.xml /build/
#COPY src /build/src/
#
#RUN mvn clean package
#COPY target/authorization-app-${version}.jar target/application.jar

FROM openjdk:17-jdk-slim
EXPOSE 8080
#FROM openjdk:17.0.9-jre-slim
COPY target/application.jar application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]
