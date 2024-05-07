FROM eclipse-temurin:21-alpine
COPY ./target/*.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
