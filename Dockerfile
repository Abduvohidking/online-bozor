FROM maven:3.6.3-openjdk-17-slim as BUILDER
LABEL authors="abduvohid"
ARG version=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/

RUN mvn clean package
COPY target/authorization-app-${version}.jar target/application.jar

FROM openjdk:17-jdk-slim
#FROM openjdk:17.0.9-jre-slim
WORKDIR /app/

COPY --from=BUILDER /build/target/application.jar /app/

CMD java -jar /app/application.jar /app/
