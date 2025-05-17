FROM eclipse-temurin:17.0.6_10-jdk-alpine

WORKDIR /app

# 1. Gradle wrapper 파일 복사
COPY gradlew gradlew
COPY gradle gradle

# 2. Jar 파일 복사
COPY build/libs/*.jar app.jar

# 3. entrypoint.sh 복사
COPY tools/entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

# 4. Gradle 실행 권한
RUN chmod +x gradlew

# 5. Docker Compose 파일 복사 (EC2에서 필요)
COPY tools/docker-compose.deploy.yml /app/tools/docker-compose.deploy.yml

EXPOSE 8080

ENTRYPOINT ["/app/entrypoint.sh"]
