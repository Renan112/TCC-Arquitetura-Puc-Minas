FROM openjdk:11
COPY application.properties application.properties
COPY app.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
