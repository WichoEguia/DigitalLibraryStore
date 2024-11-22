package com.example.DigitalLibraryStore.utils.exceptions;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException() {
        super("${message.exception.user_not_found}");
    }
}
