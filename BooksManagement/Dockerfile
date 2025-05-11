FROM eclipse-temurin:17-jdk
VOLUME /tmp
ARG JAR_FILE=target/BooksManagementApplication-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar"]
