package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Book;
import com.example.DigitalLibraryStore.utils.enums.Format;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @AfterEach
    public void afterEach() {
        bookDao.deleteAll();
    }

    @Test
    public void testSaveBook() {
        Book book = new Book("978-1-98-765412-6", "Don Quijote", "El de Don Quijote", "",
                Format.Ebook, LocalDate.now(), 101, 7.3);
        Book savedBook = bookDao.save(book);
        assertNotNull(savedBook.getId());
        assertEquals("Don Quijote", savedBook.getTitle());
    }

    @Test
    public void testFindById() {
        Book book = new Book("978-1-98-765412-6", "Don Quijote", "El de Don Quijote", "",
                Format.Ebook, LocalDate.now(), 101, 7.3);
        Book savedBook = bookDao.save(book);
        Optional<Book> result = bookDao.findById(savedBook.getId());
        assertTrue(result.isPresent());
    }

    @Test
    public void testFindByISBN() {
        String expectedIsbn = "978-1-98-765412-6";
        Book book1 = new Book("978-1-98-765412-6", "Don Quijote", "El de Don Quijote", "",
                Format.Ebook, LocalDate.now(), 101, 7.3);
        Book book2 = new Book("978-1-98-765412-3", "Don Quijote", "El de Don Quijote", "",
                Format.Hardcover, LocalDate.now(), 101, 8.3);
        Book savedBook1 = bookDao.save(book1);
        Book savedBook2 = bookDao.save(book2);
        Book result = bookDao.findByIsbn(expectedIsbn);
        assertNotNull(result);
        assertEquals(expectedIsbn, result.getIsbn());
    }

    @Test
    public void testGetByPopularidad() {
        Book book1 = new Book("978-1-98-765412-6", "Don Quijote", "El de Don Quijote", "",
                Format.Ebook, LocalDate.now(), 101, 7.3);
        Book book2 = new Book("978-1-98-765412-3", "Don Quijote", "El de Don Quijote", "",
                Format.Hardcover, LocalDate.now(), 101, 8.3);
        bookDao.save(book1);
        bookDao.save(book2);
        List<Book> result = bookDao.findAll(Sort.by(Sort.Direction.DESC, "popularity"));
        assertNotNull(result);
        assertEquals(Format.Hardcover, result.get(0).getFormat());
    }
}
