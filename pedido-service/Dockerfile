FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
RUN mvn -q -DskipTests package -pl pedido-service -am

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/pedido-service/target/pedido-service-1.0.0.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
