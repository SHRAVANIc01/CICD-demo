FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Matches the main jar while ignoring the ".jar.original" file
ARG JAR_FILE=target/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
