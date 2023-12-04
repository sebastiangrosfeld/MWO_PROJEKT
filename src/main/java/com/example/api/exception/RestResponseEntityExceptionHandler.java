package com.example.api.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
class ResponseExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, List<String>>> handleIllegalStateException(IllegalStateException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, List<String>>> handleIllegalArgumentException(IllegalArgumentException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationException(ValidationException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, List<String>>> handleException(Exception exception) {
        return buildResponse("Request is incorrect", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, List<String>>> buildResponse(List<String> errors, HttpStatus status) {
        final var content = Map.of("errors", errors);
        return new ResponseEntity<>(content, new HttpHeaders(), status);
    }

    private ResponseEntity<Map<String, List<String>>> buildResponse(String message, HttpStatus status) {
        return buildResponse(List.of(message), status);
    }

}
