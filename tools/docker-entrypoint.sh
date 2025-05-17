#!/bin/sh
echo "Running Flyway migration..."
./gradlew flywayMigrate || echo "Flyway migration failed or already applied"

echo "Starting Spring Boot application..."
exec java -jar app.jar