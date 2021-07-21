FROM openjdk:11
MAINTAINER Rebeka Batista
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} proposta-app.jar
ENTRYPOINT ["java", "-jar", "proposta-app.jar"]
