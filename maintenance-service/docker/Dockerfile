FROM amazoncorretto:21
LABEL author="rodrigogomes"
WORKDIR /app
COPY maintenance-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]