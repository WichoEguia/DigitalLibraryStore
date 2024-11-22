package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Books;
import com.example.DigitalLibraryStore.utils.Format;
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
public class BooksDaoTest {
    @Autowired
    private BookDao bookDao;

    @AfterEach
    public void afterEach() {
        bookDao.deleteAll();
    }

    @Test
    public void testSaveBook() {
        Books books = new Books("978-1-98-765412-6", "Don Quijote", "El de Don Quijote", "",
                Format.Ebook, LocalDate.now(), 101, 7.3);
        Books savedBooks = bookDao.save(books);
        assertNotNull(savedBooks.getId());
        assertEquals("Don Quijote", savedBooks.getTitle());
    }

    @Test
    public void testFindById() {
        Books books = new Books("978-1-98-765412-6", "Don Quijote", "El de Don Quijote", "",
                Format.Ebook, LocalDate.now(), 101, 7.3);
        Books savedBooks = bookDao.save(books);
        Optional<Books> result = bookDao.findById(savedBooks.getId());
        assertTrue(result.isPresent());
    }

    @Test
    public void testFindByISBN() {
        String expectedIsbn = "978-1-98-765412-6";
        Books books1 = new Books("978-1-98-765412-6", "Don Quijote", "El de Don Quijote", "",
                Format.Ebook, LocalDate.now(), 101, 7.3);
        Books books2 = new Books("978-1-98-765412-3", "Don Quijote", "El de Don Quijote", "",
                Format.Hardcover, LocalDate.now(), 101, 8.3);
        Books savedBooks1 = bookDao.save(books1);
        Books savedBooks2 = bookDao.save(books2);
        Books result = bookDao.findByIsbn(expectedIsbn);
        assertNotNull(result);
        assertEquals(expectedIsbn, result.getIsbn());
    }

    @Test
    public void testGetByPopularidad() {
        Books books1 = new Books("978-1-98-765412-6", "Don Quijote", "El de Don Quijote", "",
                Format.Ebook, LocalDate.now(), 101, 7.3);
        Books books2 = new Books("978-1-98-765412-3", "Don Quijote", "El de Don Quijote", "",
                Format.Hardcover, LocalDate.now(), 101, 8.3);
        bookDao.save(books1);
        bookDao.save(books2);
        List<Books> result = bookDao.findAll(Sort.by(Sort.Direction.DESC, "popularity"));
        assertNotNull(result);
        assertEquals(Format.Hardcover, result.get(0).getFormat());
    }
}
