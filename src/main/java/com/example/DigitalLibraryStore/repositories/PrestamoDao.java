package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrestamoDao extends JpaRepository<Prestamo, Long> {
    @Query(value = "SELECT * FROM prestamo AS p WHERE p.usuario_id = :usuarioId", nativeQuery = true)
    List<Prestamo> findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
