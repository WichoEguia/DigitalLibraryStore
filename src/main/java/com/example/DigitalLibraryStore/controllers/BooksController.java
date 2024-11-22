package com.example.DigitalLibraryStore.controllers;

import com.example.DigitalLibraryStore.dto.BookDto;
import com.example.DigitalLibraryStore.entities.Book;
import com.example.DigitalLibraryStore.services.BookServiceImpl;
import jakarta.validation.Valid;
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
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    /**
     * Retrieves a book by its unique ID.
     *
     * @param id The ID of the book.
     * @return The book details if found, or a 404 response if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new book record.
     *
     * @param bookDto The book details to create.
     * @return The created book record.
     */
    @PostMapping
    public Book createBook(@Valid @RequestBody BookDto bookDto) {
        Book book = new Book(bookDto);
        return bookService.save(book);
    }

    /**
     * Updates an existing book record.
     *
     * @param id        The ID of the book to update.
     * @param bookDto   The updated book details.
     * @return The updated book if found, or a 404 response if not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setTitle(bookDto.title());
            updatedBook.setDescription(bookDto.description());
            updatedBook.setImageUrl(bookDto.imageUrl());
            updatedBook.setFormat(bookDto.format());
            updatedBook.setPopularity(bookDto.popularity());
            updatedBook.setPublishDate(bookDto.publishDate());
            bookService.save(updatedBook);
            return ResponseEntity.ok(updatedBook);
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
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
        List<Book> books = bookService.findByTitle(title);
        return ResponseEntity.ok(books);
    }

    /**
     * Searches for books published after a specific date.
     *
     * @param publishDate The date to search after.
     * @return A list of books published after the specified date.
     */
    @GetMapping("/searchByPublishDate")
    public ResponseEntity<List<Book>> findByPublishDateAfter(@RequestParam LocalDate publishDate) {
        List<Book> books = bookService.findByPublishDateAfter(publishDate);
        return ResponseEntity.ok(books);
    }

    /**
     * Searches for a book by its ISBN.
     *
     * @param isbn The ISBN to search for.
     * @return The book matching the ISBN, or a 404 response if not found.
     */
    @GetMapping("/searchByIsbn")
    public ResponseEntity<Book> findByIsbn(@RequestParam String isbn) {
        Optional<Book> book = bookService.findByIsbn(isbn);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of the most popular books.
     *
     * @return A list of the most popular books based on the service logic.
     */
    @GetMapping("/populares")
    public ResponseEntity<List<Book>> getByPopularidad() {
        List<Book> books = bookService.getByPopularity();
        return ResponseEntity.ok(books);
    }
}
