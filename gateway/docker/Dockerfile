FROM amazoncorretto:21
LABEL author="rodrigogomes"
WORKDIR /app
COPY gateway-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]