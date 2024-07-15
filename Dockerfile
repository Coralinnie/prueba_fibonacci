# Use Amazon Corretto as the base image
FROM amazoncorretto:21-alpine-jdk

# Copy the JAR file to the image
COPY build/libs/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Specify the command to run your app
ENTRYPOINT ["java", "-jar", "/app.jar"]