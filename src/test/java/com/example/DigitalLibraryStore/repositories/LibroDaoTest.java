package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Libro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LibroDaoTest {
    @Autowired
    private LibroDao libroDao;

    @AfterEach
    public void afterEach() {
        libroDao.deleteAll();
    }

    @Test
    public void testSaveLibro() {
        Libro libro = new Libro("abc", "Don Quijote", "El de Don Quijote", "",
                "eBook", LocalDate.now(), 101, 7.3);
        Libro savedLibro = libroDao.save(libro);
        assertNotNull(savedLibro.getId());
        assertEquals("Don Quijote", savedLibro.getTitulo());
    }

    @Test
    public void testFindById() {
        Libro libro = new Libro("abc", "Don Quijote", "El de Don Quijote", "",
                "eBook", LocalDate.now(), 101, 7.3);
        Libro savedLibro = libroDao.save(libro);
        Optional<Libro> result = libroDao.findById(savedLibro.getId());
        assertTrue(result.isPresent());
    }

    @Test
    public void testFindByISBN() {
        Libro libro1 = new Libro("abc", "Don Quijote", "El de Don Quijote", "",
                "eBook", LocalDate.now(), 101, 7.3);
        Libro libro2 = new Libro("def", "Don Quijote", "El de Don Quijote", "",
                "fisico", LocalDate.now(), 101, 8.3);
        Libro savedLibro1 = libroDao.save(libro1);
        Libro savedLibro2 = libroDao.save(libro2);
        Libro result = libroDao.findByIsbn("def");
        assertNotNull(result);
        assertEquals("def", result.getIsbn());
    }

    @Test
    public void testGetByPopularidad() {
        Libro libro1 = new Libro("abc", "Don Quijote", "El de Don Quijote", "",
                "eBook", LocalDate.now(), 101, 7.3);
        Libro libro2 = new Libro("def", "Don Quijote", "El de Don Quijote", "",
                "fisico", LocalDate.now(), 101, 8.3);
        libroDao.save(libro1);
        libroDao.save(libro2);
        List<Libro> result = libroDao.findAll(Sort.by(Sort.Direction.DESC, "popularidad"));
        assertNotNull(result);
        assertEquals("fisico", result.get(0).getFormato());
    }
}
