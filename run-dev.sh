#!/bin/bash

set -e

cleanup() {
  echo "🧹 Cleaning up Docker containers..."
  docker compose -f tools/docker-compose.yml down -v
}
trap cleanup EXIT

echo "🐳 Starting Docker containers via Docker Compose..."
docker compose -f tools/docker-compose.yml up -d

echo "⏳ Waiting for MySQL to become healthy..."
until docker exec factcheck-mysql mysqladmin ping -h "localhost" --silent; do
  sleep 2
done

echo "✅ MySQL is ready. Starting Spring Boot application..."
./gradlew flywayClean flywayMigrate
./gradlew bootRun --args='--spring.profiles.active=local'