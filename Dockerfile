FROM openjdk:22-rc-oraclelinux9 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
COPY mvnw ./
COPY .mvn ./.mvn
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests
FROM openjdk:22-rc-oraclelinux9
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080