package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByFechaCreacion(LocalDateTime createdDate);
}
