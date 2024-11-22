package com.example.DigitalLibraryStore.config;

import org.apache.coyote.BadRequestException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.*;

/**
 * GlobalExceptionHandler is a class that handles various exceptions globally across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException.
     * Returns a BAD_REQUEST (400) response with the exception message.
     *
     * @param ex The exception that occurred.
     * @param request The web request that caused the exception.
     * @return A ResponseEntity containing the exception message and a BAD_REQUEST status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ConfigDataResourceNotFoundException.
     * Returns a NOT_FOUND (404) response with the exception message.
     *
     * @param ex The exception that occurred.
     * @param request The web request that caused the exception.
     * @return A ResponseEntity containing the exception message and a NOT_FOUND status.
     */
    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ConfigDataResourceNotFoundException ex,
                                                                  WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    /**
     * Handles BadRequestException.
     * Returns a BAD_REQUEST (400) response with the exception message.
     *
     * @param ex The exception that occurred.
     * @param request The web request that caused the exception.
     * @return A ResponseEntity containing the exception message and a BAD_REQUEST status.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), BAD_REQUEST);
    }

    /**
     * Handles any other generic exception that is not caught by the other handlers.
     * Returns an INTERNAL_SERVER_ERROR (500) response with the exception message.
     *
     * @param ex The exception that occurred.
     * @param request The web request that caused the exception.
     * @return A ResponseEntity containing the exception message and an INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), INTERNAL_SERVER_ERROR);
    }
}
