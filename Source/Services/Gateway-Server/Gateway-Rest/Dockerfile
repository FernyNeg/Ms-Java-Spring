FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/Gateway-Rest.jar app.jar
ENV EUREKA_URL NULL
ENV AUTH_URI NULL
ENTRYPOINT ["java","-jar","/app.jar"]