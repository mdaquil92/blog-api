package com.blogapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Blog REST API",
        version = "1.0",
        description = "A complete Blog REST API with JWT Authentication, Posts, Comments, and User management.",
        contact = @Contact(name = "Md Aquil", email = "mirzaaquil786@gmail.com")
    )
)
public class BlogApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }
}
