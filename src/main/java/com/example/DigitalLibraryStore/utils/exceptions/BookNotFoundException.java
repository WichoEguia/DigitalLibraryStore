package com.example.DigitalLibraryStore.utils.exceptions;

public class BookNotFoundException extends ResourceNotFoundException {
    public BookNotFoundException() {
        super("${message.exception.book_not_found}");
    }
}
