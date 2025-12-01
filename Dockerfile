# Build con Maven
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x ./mvnw

#Instalacion wget
RUN  apt-get update && apt-get install -y wget

#Compilar el proyecto
RUN ./mvnw clean package -DskipTests

# Runtime solo el .jar
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
