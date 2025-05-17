FROM eclipse-temurin:17.0.6_10-jdk-alpine

WORKDIR /app

# 전체 프로젝트 복사
COPY . .

# Gradle 및 entrypoint 실행 권한 부여
RUN chmod +x gradlew
RUN chmod +x /app/tools/entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/app/tools/entrypoint.sh"]
