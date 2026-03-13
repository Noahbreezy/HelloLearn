package com.example.demo.Error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiError handleIllegalArgument(IllegalArgumentException exception, HttpServletRequest request) {
        return new ApiError(
                "invalid-argument",
                exception.getMessage() != null ? exception.getMessage() : "invalid argument",
                request.getRequestURI(),
                Instant.now()
        );
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ApiError> handleArithmeticException(ArithmeticException exception, HttpServletRequest request) {
        ApiError body = new ApiError(
                "arithmetic-error",
                exception.getMessage() != null ? exception.getMessage() : "arithmetic-error",
                request.getRequestURI(),
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(body);
    }
}
