# Multi Stage Build
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml ./

RUN mvn -DskipTests dependency:go-offline

COPY src ./src

RUN mvn -DskipTests clean package

FROM eclipse-temurin:21-jre-alpine

EXPOSE 8761

COPY --from=build /app/target/*.jar /app/app.jar

ENTRYPOINT ["sh","-c","java -jar /app/app.jar"]