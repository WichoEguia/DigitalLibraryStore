package com.example.DigitalLibraryStore.controllers;

import com.example.DigitalLibraryStore.entities.Libro;
import com.example.DigitalLibraryStore.services.LibroServiceImpl;
import com.example.DigitalLibraryStore.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LibrosController {
    private final LibroServiceImpl libroService;

    @Autowired
    public LibrosController(LibroServiceImpl libroService) {
        this.libroService = libroService;
    }
    
    @GetMapping("/all")
    public List<Libro> getAllLibros() {
        return libroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Optional<Libro> libro = libroService.findById(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroService.save(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        Optional<Libro> libro = libroService.findById(id);
        if (libro.isPresent()) {
            Libro updatedLibro = libro.get();
            updatedLibro.setTitulo(libroDetails.getTitulo());
            updatedLibro.setDescripcion(libroDetails.getDescripcion());
            updatedLibro.setPortadaUrl(libroDetails.getPortadaUrl());
            updatedLibro.setFormato(libroDetails.getFormato());
            updatedLibro.setFechaPublicacion(libroDetails.getFechaPublicacion());
            libroService.save(updatedLibro);
            return ResponseEntity.ok(updatedLibro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        if (libroService.findById(id).isPresent()) {
            libroService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByTitulo")
    public ResponseEntity<List<Libro>> findByTitulo(@RequestParam String titulo) {
        List<Libro> libros = libroService.findByTitulo(titulo);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/searchByFechaPublicacion")
    public ResponseEntity<List<Libro>> findByFechaPublicacionAfter(@RequestParam LocalDate fechaPublicacion) {
        List<Libro> libros = libroService.findByFechaPublicacionAfter(fechaPublicacion);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/searchByIsbn")
    public ResponseEntity<Libro> findByIsbn(@RequestParam String isbn) {
        Optional<Libro> libro = libroService.findByIsbn(isbn);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/populares")
    public ResponseEntity<List<Libro>> getByPopularidad() {
        List<Libro> libro = libroService.getByPopularidad();
        return ResponseEntity.ok(libro);
    }
}
