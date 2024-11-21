package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Libro;
import com.example.DigitalLibraryStore.interfaces.ILibroService;
import com.example.DigitalLibraryStore.repositories.LibroDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class LibroServiceImpl implements ILibroService {
    private LibroDao libroDao;

    @Autowired
    public LibroServiceImpl(LibroDao libroDao) {
        this.libroDao = libroDao;
    }

    @Override
    public List<Libro> findAll() {
        return libroDao.findAll();
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return libroDao.findById(id);
    }

    @Override
    public Libro save(@Valid Libro libro) {
        return libroDao.save(libro);
    }

    @Override
    public void deleteById(Long id) {
        libroDao.deleteById(id);
    }

    @Override
    public List<Libro> findByTitulo(String titulo) {
        return libroDao.findByTitulo(titulo);
    }

    @Override
    public List<Libro> findByFechaPublicacionAfter(LocalDate fechaPublicacion) {
        return libroDao.findByFechaPublicacion(fechaPublicacion);
    }

    @Override
    public Optional<Libro> findByIsbn(String isbn) {
        return Optional.ofNullable(libroDao.findByIsbn(isbn));
    }

    @Override
    public List<Libro> getByPopularidad() {
        return libroDao.findAll(Sort.by(Sort.Direction.DESC, "popularidad"));
    }
}
