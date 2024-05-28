FROM eclipse-temurin:17-jdk-alpine
RUN apk add --no-cache redis
VOLUME /tmp
COPY build/libs/* app.jar
ENTRYPOINT ["java","-jar","app.jar"]