package com.example.DigitalLibraryStore.controllers;

import com.example.DigitalLibraryStore.entities.Books;
import com.example.DigitalLibraryStore.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing books in the digital library system.
 */
@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    private final BookServiceImpl bookService;

    /**
     * Constructor for dependency injection of the book service.
     *
     * @param bookService The service for managing book records.
     */
    @Autowired
    public BooksController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    /**
     * Retrieves all books in the system.
     *
     * @return A list of all books.
     */
    @GetMapping("/all")
    public List<Books> getAllBooks() {
        return bookService.findAll();
    }

    /**
     * Retrieves a book by its unique ID.
     *
     * @param id The ID of the book.
     * @return The book details if found, or a 404 response if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        Optional<Books> book = bookService.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new book record.
     *
     * @param books The book details to create.
     * @return The created book record.
     */
    @PostMapping
    public Books createBook(@RequestBody Books books) {
        return bookService.save(books);
    }

    /**
     * Updates an existing book record.
     *
     * @param id           The ID of the book to update.
     * @param booksDetails The updated book details.
     * @return The updated book if found, or a 404 response if not.
     */
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

    /**
     * Deletes a book record by its unique ID.
     *
     * @param id The ID of the book to delete.
     * @return A 204 response if the book was deleted, or a 404 response if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Searches for books by their title.
     *
     * @param title The title to search for.
     * @return A list of books matching the title.
     */
    @GetMapping("/searchByTitle")
    public ResponseEntity<List<Books>> findByTitle(@RequestParam String title) {
        List<Books> books = bookService.findByTitle(title);
        return ResponseEntity.ok(books);
    }

    /**
     * Searches for books published after a specific date.
     *
     * @param publishDate The date to search after.
     * @return A list of books published after the specified date.
     */
    @GetMapping("/searchByPublishDate")
    public ResponseEntity<List<Books>> findByPublishDateAfter(@RequestParam LocalDate publishDate) {
        List<Books> books = bookService.findByPublishDateAfter(publishDate);
        return ResponseEntity.ok(books);
    }

    /**
     * Searches for a book by its ISBN.
     *
     * @param isbn The ISBN to search for.
     * @return The book matching the ISBN, or a 404 response if not found.
     */
    @GetMapping("/searchByIsbn")
    public ResponseEntity<Books> findByIsbn(@RequestParam String isbn) {
        Optional<Books> book = bookService.findByIsbn(isbn);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of the most popular books.
     *
     * @return A list of the most popular books based on the service logic.
     */
    @GetMapping("/populares")
    public ResponseEntity<List<Books>> getByPopularidad() {
        List<Books> books = bookService.getByPopularity();
        return ResponseEntity.ok(books);
    }
}
