FROM eclipse-temurin:17.0.6_10-jdk-alpine
WORKDIR /app

COPY build/libs/*.jar app.jar

COPY tools/docker-compose.deploy.yml /app/tools/docker-compose.deploy.yml
COPY tools/docker-entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["sh", "/app/entrypoint.sh"]