# Use OpenJDK 21 as build stage
FROM eclipse-temurin:21-jdk AS build

# Set working directory
WORKDIR /app

# Copy mvnw and pom.xml first (for dependency caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Fix permission issue for mvnw
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the project
COPY src src

# Package the application
RUN ./mvnw package -DskipTests

# Runtime image
FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
