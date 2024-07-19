FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN maven clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --frombuild /target/user-service-0.0.1-SNAPSHOT.jar ascii-art-generator.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","user-service.jar"]