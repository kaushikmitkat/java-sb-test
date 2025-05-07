FROM maven:3.9.9-eclipse-temurin-21 AS builder
LABEL authors="kaushikkarandikar"

WORKDIR /app

COPY pom.xml /app

RUN mvn dependency:go-offline -B

COPY src /app/src

RUN mvn clean package

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/target/patient-service-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]