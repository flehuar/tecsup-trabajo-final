FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
RUN mvn -q -DskipTests package -pl pago-service -am

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/pago-service/target/pago-service-1.0.0.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
