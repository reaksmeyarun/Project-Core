# Use a base image with Maven and OpenJDK
FROM maven:3.9.4-openjdk-21-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the project files
COPY pom.xml .
COPY gateway/pom.xml ./gateway/
COPY core/pom.xml ./core/
COPY user/pom.xml ./user/

# Copy the source code
COPY gateway/src ./gateway/src
COPY core/src ./core/src
COPY user/src ./user/src

# Build the project
RUN mvn clean install -DskipTests

# Use a smaller base image for the final application
FROM openjdk:21-jdk-slim

# Set the working directory in the final image
WORKDIR /app

# Copy the built jar files from the build stage
COPY gateway/target/gateway-0.0.1-SNAPSHOT.jar app.jar
COPY core/target/core-0.0.1-SNAPSHOT.jar core-app.jar
COPY user/target/user-0.0.1-SNAPSHOT.jar user-app.jar

# Expose the ports your applications will run on
EXPOSE 8080
EXPOSE 8081
EXPOSE 8082

# Command to run the applications
CMD ["sh", "-c", "java -jar core-app.jar & java -jar user-app.jar & java -jar app.jar"]
