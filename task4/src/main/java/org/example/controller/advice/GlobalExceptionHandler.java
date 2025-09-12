package org.example.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("Entity not found: {}", e.getMessage());
        return Map.of("ERROR: ", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException .class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Validation failed: {}", e.getMessage());
        return Map.of("ERROR: ", e.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.BadGateway.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public Map<String, String> handleHttpServerErrorException(HttpServerErrorException e) {
        log.error("Error connecting to external service: {}", e.getMessage());
        return Map.of("ERROR: ", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("Invalid parameter type: {}", e.getMessage());
        return Map.of("ERROR: Invalid parameter type", e.getMessage());
    }

}
