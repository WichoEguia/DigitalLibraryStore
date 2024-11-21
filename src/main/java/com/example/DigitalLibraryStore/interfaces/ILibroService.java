package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Libro;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ILibroService {
    public List<Libro> findAll();
    public Optional<Libro> findById(Long id);
    public Libro save(@Valid Libro libro);
    public void deleteById(Long id);
    public List<Libro> findByTitulo(String titulo);
    public List<Libro> findByFechaPublicacionAfter(LocalDate fechaPublicacion);
    public Optional<Libro> findByIsbn(String isbn);
    public List<Libro> getByPopularidad();
}
