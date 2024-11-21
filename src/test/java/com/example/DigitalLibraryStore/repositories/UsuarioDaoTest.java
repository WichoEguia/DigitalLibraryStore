package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsuarioDaoTest {
    @Autowired
    private UsuarioDao usuarioDao;

    @AfterEach
    public void afterEach() {
        usuarioDao.deleteAll();
    }

    private Usuario createUsuario() {
        Usuario usuario = new Usuario("John Doe", "john.doe@example.com",
                "secure123", true);
        return usuarioDao.save(usuario);
    }

    @Test
    public void testSaveUsuario() {
        Usuario savedUsuario = createUsuario();
        assertNotNull(savedUsuario);
        assertEquals("John Doe", savedUsuario.getNombre());
    }

    @Test
    public void testFindById() {
        Usuario savedUsuario = createUsuario();
        Optional<Usuario> result = usuarioDao.findById(savedUsuario.getId());
        assertNotNull(savedUsuario);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getNombre());
    }

    @Test
    public void testFindByNombre() {
        createUsuario();
        List<Usuario> result = usuarioDao.findByNombre("John Doe");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindByEmail() {
        createUsuario();
        Optional<Usuario> result = usuarioDao.findByEmail("john.doe@example.com");
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getNombre());
    }
}