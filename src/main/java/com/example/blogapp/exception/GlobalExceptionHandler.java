package com.example.blogapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails resourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException
    ) {
        String message = resourceNotFoundException.getMessage();
        log.info("Resource is not found with id: {} and exception: {}",
                resourceNotFoundException.getFieldName(),
                resourceNotFoundException.fillInStackTrace());
        return ErrorDetails.builder().errorId(UUID.randomUUID().toString())
                .message(message).build();
    }
}
