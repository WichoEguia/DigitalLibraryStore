package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.dto.BookDto;
import com.example.DigitalLibraryStore.entities.Book;
import com.example.DigitalLibraryStore.interfaces.IBookService;
import com.example.DigitalLibraryStore.repositories.BookDao;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the IBookService interface,handles CRUD operations
 * for managing books using the BookDao repository.
 */
@Service
@Validated
public class BookServiceImpl implements IBookService {

    private final BookDao bookDao;

    /**
     * Constructor for BookServiceImpl that accepts a BookDao instance for dependency injection.
     *
     * @param bookDao The DAO for interacting with book data.
     */
    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    /**
     * Retrieves all books from the data source.
     *
     * @return A list of all books.
     */
    @Transactional
    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    /**
     * Retrieves a book by its unique ID.
     *
     * @param id The ID of the book.
     * @return An Optional containing the book if found, or an empty Optional if not found.
     */
    @Transactional
    @Override
    public Optional<Book> findById(Long id) {
        return bookDao.findById(id);
    }

    /**
     * Saves a new or updated book to the data source.
     *
     * @param book The book to save.
     * @return The saved book.
     */
    @Transactional
    @Override
    public Book save(@Valid Book book) {
        return bookDao.save(book);
    }

    /**
     * Deletes a book by its unique ID.
     *
     * @param id The ID of the book to delete.
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }

    /**
     * Retrieves a list of books that match the specified title.
     *
     * @param title The title of the books to search for.
     * @return A list of books with the specified title.
     */
    @Transactional
    @Override
    public List<Book> findByTitle(String title) {
        return bookDao.findByTitle(title);
    }

    /**
     * Retrieves a list of books that were published after the specified date.
     *
     * @param publishDate The publish date to filter the books by.
     * @return A list of books published after the specified date.
     */
    @Transactional
    @Override
    public List<Book> findByPublishDateAfter(LocalDate publishDate) {
        return bookDao.findByPublishDate(publishDate);
    }

    /**
     * Retrieves a book by its ISBN.
     *
     * @param isbn The ISBN of the book to search for.
     * @return An Optional containing the book if found, or an empty Optional if not found.
     */
    @Transactional
    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(bookDao.findByIsbn(isbn));
    }

    /**
     * Retrieves a list of books ordered by their popularity in descending order.
     *
     * @return A list of books ordered by popularity.
     */
    @Transactional
    @Override
    public List<Book> getByPopularity() {
        return bookDao.findAll(Sort.by(Sort.Direction.DESC, "popularidad"));
    }
}
