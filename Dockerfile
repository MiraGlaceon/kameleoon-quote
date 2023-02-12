FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/kameleoon-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} kameleoon.jar
ENTRYPOINT ["java","-jar","kameleoon.jar"]