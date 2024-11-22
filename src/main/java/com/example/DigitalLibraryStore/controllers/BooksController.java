package com.example.DigitalLibraryStore.controllers;

import com.example.DigitalLibraryStore.entities.Books;
import com.example.DigitalLibraryStore.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BooksController {
    private final BookServiceImpl bookService;

    @Autowired
    public BooksController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/all")
    public List<Books> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        Optional<Books> book = bookService.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Books createBook(@RequestBody Books books) {
        return bookService.save(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books booksDetails) {
        Optional<Books> book = bookService.findById(id);
        if (book.isPresent()) {
            Books updatedBooks = book.get();
            updatedBooks.setTitle(booksDetails.getTitle());
            updatedBooks.setDescription(booksDetails.getDescription());
            updatedBooks.setImageUrl(booksDetails.getImageUrl());
            updatedBooks.setFormat(booksDetails.getFormat());
            updatedBooks.setPublishDate(booksDetails.getPublishDate());
            bookService.save(updatedBooks);
            return ResponseEntity.ok(updatedBooks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByTitle")
    public ResponseEntity<List<Books>> findByTitle(@RequestParam String title) {
        List<Books> books = bookService.findByTitle(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/searchByPublishDate")
    public ResponseEntity<List<Books>> findByPublishDateAfter(@RequestParam LocalDate publishDate) {
        List<Books> books = bookService.findByPublishDateAfter(publishDate);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/searchByIsbn")
    public ResponseEntity<Books> findByIsbn(@RequestParam String isbn) {
        Optional<Books> book = bookService.findByIsbn(isbn);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/populares")
    public ResponseEntity<List<Books>> getByPopularidad() {
        List<Books> books = bookService.getByPopularity();
        return ResponseEntity.ok(books);
    }
}
