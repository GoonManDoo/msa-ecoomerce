FROM openjdk:17-ea-jdk-slim
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY target/config-service-1.0.jar ConfigServer.jar
ENTRYPOINT ["java", "-jar", "ConfigServer.jar"]