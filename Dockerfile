FROM maven:3.8.5-openjdk-17 AS build
COPY . .

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","user-service.jar"]