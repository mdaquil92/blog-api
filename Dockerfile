FROM maven:3.9.4-eclipse-temurin-21-alpine

WORKDIR /app

COPY ./target/blog-api-1.0.0.jar .

EXPOSE 8080

CMD ["java","-jar","blog-api-1.0.0.jar"]