package com.example.DigitalLibraryStore.config;

import com.example.DigitalLibraryStore.utils.exceptions.ResourceNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

/**
 * GlobalExceptionHandler is a class that handles various exceptions globally across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles ResourceNotFoundException custom exception.
     * Returns a NOT_FOUND (404) response with the exception message.
     *
     * @param ex The exception that occurred.
     * @return A ResponseEntity containing the exception message and a NOT_FOUND status.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    /**
     * Handles MethodArgumentNotValidException.
     * Returns a BAD_REQUEST (400) response with the exception message.
     *
     * @param ex The exception that occurred.
     * @return A ResponseEntity containing the exception message and a BAD_REQUEST status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), BAD_REQUEST);
    }

    /**
     * Handles ConfigDataResourceNotFoundException.
     * Returns a NOT_FOUND (404) response with the exception message.
     *
     * @param ex The exception that occurred.
     * @return A ResponseEntity containing the exception message and a NOT_FOUND status.
     */
    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ConfigDataResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    /**
     * Handles BadRequestException.
     * Returns a BAD_REQUEST (400) response with the exception message.
     *
     * @param ex The exception that occurred.
     * @return A ResponseEntity containing the exception message and a BAD_REQUEST status.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), BAD_REQUEST);
    }

    /**
     * Handles any other generic exception that is not caught by the other handlers.
     * Returns an INTERNAL_SERVER_ERROR (500) response with the exception message.
     *
     * @param ex The exception that occurred.
     * @return A ResponseEntity containing the exception message and an INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), INTERNAL_SERVER_ERROR);
    }
}
