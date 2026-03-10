FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
RUN mvn -q -DskipTests package -pl entrega-service -am

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/entrega-service/target/entrega-service-1.0.0.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
