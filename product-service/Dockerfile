# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS builder

# Install Maven directly
RUN apt-get update && apt-get install -y maven

WORKDIR /app

# Copy only necessary files to build the app
COPY pom.xml .
COPY src ./src

# Package the app (you can also do 'mvn clean install')
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the built jar from the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Run the jar
CMD ["java", "-jar", "app.jar"]