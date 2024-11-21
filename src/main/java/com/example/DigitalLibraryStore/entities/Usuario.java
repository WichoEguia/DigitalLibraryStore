package com.example.DigitalLibraryStore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String nombre;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prestamo> prestamos;

    public Usuario(String nombre, String email, String password, boolean enabled) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public Usuario(Long id, String nombre, String email, String password, boolean enabled) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.fechaCreacion = LocalDateTime.now();
        this.prestamos = new HashSet<>();
    }
}
