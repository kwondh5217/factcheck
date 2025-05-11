FROM eclipse-temurin:17.0.15-jdk-alpine
WORKDIR /app

COPY build/libs/*.jar app.jar

COPY tools/docker-compose.yml /app/tools/docker-compose.yml

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
