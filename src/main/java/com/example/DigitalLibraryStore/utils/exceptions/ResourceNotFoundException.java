package com.example.DigitalLibraryStore.utils.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException {
    public ResourceNotFoundException() {
        super("${message.exception.default.resource_not_found}");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
