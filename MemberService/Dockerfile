FROM eclipse-temurin:17-jdk-alpine
RUN apk add --no-cache redis
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]