package com.example.DigitalLibraryStore.entities;

import com.example.DigitalLibraryStore.utils.annotations.MinPages;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "libro")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column
    private String portadaUrl;

    @Column
    private String formato;

    @Column(nullable = false)
    private LocalDate fechaPublicacion;

    @Column
    @MinPages(value = 100)
    private Integer numeroPaginas;

    @Column(nullable = false)
    @Min(value = 0, message = "La popularidad no puede ser menor que 0.")
    @Max(value = 10, message = "La popularidad no puede ser mayor que 100.")
    private double popularidad;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prestamo> prestamos;

    public Libro(String isbn, String titulo, String descripcion, String portadaUrl, String formato,
                 LocalDate fechaPublicacion, Integer numeroPaginas, double popularidad) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.portadaUrl = portadaUrl;
        this.formato = formato;
        this.fechaPublicacion = fechaPublicacion;
        this.numeroPaginas = numeroPaginas;
        this.popularidad = popularidad;
    }
}
