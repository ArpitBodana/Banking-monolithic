# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .
#
# FROM openjdk:17.0.1-jdk-slim
# COPY --from=build /target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
# EXPOSE 8081
# ENTRYPOINT ["java","-jar","user-service.jar"]


#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/user-service-0.0.1-SNAPSHOT.jar /usr/local/lib/user-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/falcon.jar"]