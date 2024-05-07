FROM maven:3.9.6-eclipse-temurin-21-alpine as build
COPY settings.xml ~/.m2/settings.xml
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-alpine
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
