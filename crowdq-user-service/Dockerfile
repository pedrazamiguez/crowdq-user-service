FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app
COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/crowdq-user-service-boot/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
