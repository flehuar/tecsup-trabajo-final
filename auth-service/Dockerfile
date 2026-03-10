FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
RUN mvn -q -DskipTests package -pl auth-service -am

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/auth-service/target/auth-service-1.0.0.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
