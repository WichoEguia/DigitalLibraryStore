package com.example.DigitalLibraryStore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prestamo")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @Column(nullable = false)
    private LocalDateTime fechaPrestamo;

    @Column(nullable = false)
    private LocalDateTime fechaDevolucion;

    public Prestamo(Usuario usuario, Libro libro, LocalDateTime fechaDevolucion) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = LocalDateTime.now();
        this.fechaDevolucion = fechaDevolucion;
    }
}
