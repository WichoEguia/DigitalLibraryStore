package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LibroDao extends JpaRepository<Libro, Long> {
    List<Libro> findByTitulo(String titulo);
    List<Libro> findByFechaPublicacion(LocalDate fechaPublicacion);
    Libro findByIsbn(String isbn);
}
