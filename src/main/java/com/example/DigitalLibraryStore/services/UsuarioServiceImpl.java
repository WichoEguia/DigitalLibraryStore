package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Usuario;
import com.example.DigitalLibraryStore.interfaces.IUsuarioService;
import com.example.DigitalLibraryStore.repositories.PrestamoDao;
import com.example.DigitalLibraryStore.repositories.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private UsuarioDao usuarioDao;

    @Autowired
    public UsuarioServiceImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioDao.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioDao.deleteById(id);
    }

    @Override
    public Optional<List<Usuario>> findByNombre(String name) {
        return Optional.ofNullable(usuarioDao.findByNombre(name));
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

    @Override
    public List<Usuario> findByFechaCreacion(LocalDateTime createdDate) {
        return usuarioDao.findByFechaCreacion(createdDate);
    }
}
