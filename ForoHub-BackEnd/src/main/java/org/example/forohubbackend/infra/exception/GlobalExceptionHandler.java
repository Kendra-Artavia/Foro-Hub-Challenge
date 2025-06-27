package org.example.forohubbackend.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.example.forohubbackend.domain.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 * Captures common exceptions and returns appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles 404 errors when an entity is not found in the database
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound() {
        return ResponseEntity.notFound().build();
    }

    // Handles validation errors when @Valid fails (e.g., missing or invalid request fields)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors()
                .stream()
                .map(ValidationErrorData::new)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    // Handles custom validation exceptions (thrown manually)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleCustomValidationError(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // DTO for sending validation error details to the client
    private record ValidationErrorData(String field, String message) {
        public ValidationErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
