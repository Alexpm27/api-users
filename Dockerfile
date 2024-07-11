FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/users-micro.jar users-micro.jar
EXPOSE 8080
CMD ["java","-jar","users-micro.jar"]