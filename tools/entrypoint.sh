#!/bin/sh
set -e

echo "🚀 Running Flyway migration..."
./gradlew flywayMigrate

echo "✅ Starting API"
exec java -jar build/libs/*.jar
