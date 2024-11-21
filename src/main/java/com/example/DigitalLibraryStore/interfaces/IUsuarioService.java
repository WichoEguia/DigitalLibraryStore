package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> findAll();
    public Optional<Usuario> findById(Long id);
    public Usuario save(Usuario usuario);
    public void deleteById(Long id);
    public Optional<List<Usuario>> findByNombre(String name);
    public Optional<Usuario> findByEmail(String email);
    public List<Usuario> findByFechaCreacion(LocalDateTime createdDate);
}
