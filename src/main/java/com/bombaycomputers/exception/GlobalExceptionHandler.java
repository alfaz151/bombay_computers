package com.bombaycomputers.exception;

import com.bombaycomputers.dto.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles @Valid validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", ")); // combine multiple errors

        ApiResponse response = new ApiResponse("400", "", errorMessage, "unsuccess");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Optionally handle other validation issues (like @Validated on params)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.joining(", "));

        ApiResponse response = new ApiResponse("400", "", errorMessage, "unsuccess");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Generic error fallback (optional)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
        ApiResponse response = new ApiResponse("500", "", ex.getMessage(), "unsuccess");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
