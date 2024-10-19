FROM maven:3.9.9-eclipse-temurin-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-alpine
COPY --from=build /target/*.jar user-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","user-service.jar"]


