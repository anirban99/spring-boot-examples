FROM --platform=linux/amd64 adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ARG ARTIFACTS=build/libs
ARG JAR_FILE=${ARTIFACTS}/spring-boot-mobility-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} spring-boot-mobility-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/spring-boot-mobility-0.0.1-SNAPSHOT.jar"]