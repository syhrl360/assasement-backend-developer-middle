# Use a maven to build project from docker
FROM maven:3.9.14-eclipse-temurin-17-alpine AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package -DskipTests

# Use a lightweight JDK base image
FROM bellsoft/liberica-openjdk-alpine:17.0.18
# The maintainer of the image
LABEL  maintainer="not.syahrul@outlook.co.id"
# Set the working directory inside the container
WORKDIR /app
# Copy the JAR file from /build/targer directory in MAVEN_BUILD stage to the /app directory to the current stage 
COPY --from=MAVEN_BUILD /build/target/*.jar /app/
# Define the command to run the application when the container starts
ENTRYPOINT ["java","-jar","docker.authority-0.0.1.jar"]
