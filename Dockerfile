FROM openjdk:16-jdk
COPY target/camel-microservice-demo1-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]