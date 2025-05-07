FROM maven:3.9.9-eclipse-temurin-21 AS builder
LABEL authors="kaushikkarandikar"

WORKDIR /app

COPY pom.xml /app

RUN mvn dependency:go-offline -B

COPY src /app/src

RUN mvn clean package

COPY target/patient-service-0.0.1-SNAPSHOT.jar /app/patient-service.jar

CMD ["java", "-jar", "/app/patient-service.jar"]