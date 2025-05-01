package com.sultanlinjawi.library.controllers;

import com.sultanlinjawi.library.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.graphql.client.FieldAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // All unexpected errors
        log.error("Caught exception", ex);
        var error =
                ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("An unexpected error occurred")
                        .build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        log.error("Invalid argument: ", ex);
        var error =
                ErrorResponse.builder()
                        .status(HttpStatus.CONFLICT)
                        .message(ex.getMessage())
                        .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("User not found with username: ", ex);
        var error =
                ErrorResponse.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .message(ex.getMessage())
                        .build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(FieldAccessException.class)
    public ResponseEntity<ErrorResponse> handleFieldAccessException(FieldAccessException ex) {
        log.error("Graphql query parsing exception: " + ex);
        var error =
                ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(ex.getMessage())
                        .build();
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
