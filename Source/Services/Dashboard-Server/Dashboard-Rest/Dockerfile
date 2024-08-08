FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/Dashboard-Rest.jar app.jar
ENV EUREKA_URL NULL
ENV CONFIG_SERVER NULL
ENTRYPOINT ["java","-jar","/app.jar"]