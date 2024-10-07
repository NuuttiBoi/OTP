# Use Maven image to build the application
FROM maven:latest AS build

# Set working directory inside the container
WORKDIR /app

# Copy the pom.xml to download dependencies first (caching optimization)
COPY pom.xml /app/

# Copy the entire project to the container
COPY . /app/

# Package the application using Maven
RUN mvn clean install

# Use a lightweight JDK image to run the application
FROM openjdk:17-jdk-slim

# Install OpenJFX and Xvfb
RUN apt-get update && apt-get install -y \
    openjfx \
    xvfb \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the built JAR from the Maven build stage
COPY --from=build /app/target/Test.jar /app/Test.jar

# Set the display environment variable
ENV DISPLAY=:99

# Start Xvfb and run the JavaFX application
CMD ["sh", "-c", "Xvfb :99 -screen 0 1024x768x24 & java -jar Test.jar"]
