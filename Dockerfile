FROM gradle:7.4.2-jdk11-alpine
WORKDIR /app
COPY src ./src
COPY build.gradle.kts ./build.gradle.kts
COPY gradle.properties ./gradle.properties
COPY settings.gradle.kts ./settings.gradle.kts
RUN gradle test
