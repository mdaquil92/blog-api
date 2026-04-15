package com.blogapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BlogApiException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public BlogApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
