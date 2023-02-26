package com.example.blogapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails resourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException,
            WebRequest webRequest
    ) {
        String message = resourceNotFoundException.getMessage();
        log.info("Resource is not found with id: {} and exception: {}",
                resourceNotFoundException.getFieldName(),
                resourceNotFoundException.fillInStackTrace());
        return ErrorDetails.builder().errorId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now())
                .path(webRequest.getDescription(false))
                .message(message).build();
    }

    @ExceptionHandler(BlogApiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails blogApiException(
            BlogApiException blogApiException,
            WebRequest webRequest
    ) {
        String message = blogApiException.getMessage();
        log.info("Resource is not found with id: {} and exception: {}",
                blogApiException.getMessage(),
                blogApiException.fillInStackTrace());
        return ErrorDetails.builder().errorId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now())
                .path(webRequest.getDescription(false))
                .message(message).build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String message = ((FieldError) objectError).getDefaultMessage();
            errors.put(fieldName, message);
        }));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails globalException(
            Exception exception,
            WebRequest webRequest
    ) {
        String message = exception.getMessage();
        log.info("Resource is not found with id: {} and exception: {}",
                exception.getMessage(),
                exception.fillInStackTrace());
        return ErrorDetails.builder().errorId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now())
                .path(webRequest.getDescription(false))
                .message(message).build();
    }
}
