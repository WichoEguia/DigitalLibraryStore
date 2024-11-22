package com.example.DigitalLibraryStore.utils.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super("${message.exception.default.resource_not_found}");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
