package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Prestamo;
import com.example.DigitalLibraryStore.entities.Usuario;
import com.example.DigitalLibraryStore.repositories.UsuarioDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceImplTest {
    @Mock
    private UsuarioDao usuarioDao;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    public void testFindAll() {
        List<Usuario> usuarios = Arrays.asList(
            new Usuario(1L, "John Doe", "john.doe@example.com", "secure123",
                    true),
            new Usuario(2L,"Jane Doe", "jane.doe@example.com", "secure321",
                    true)
        );
        Mockito.when(usuarioDao.findAll()).thenReturn(usuarios);
        List<Usuario> result = usuarioService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void findById() {
        Usuario usuario = new Usuario(1L, "John Doe", "john.doe@example.com", "secure123",
                true);
        Mockito.when(usuarioDao.findById(1L)).thenReturn(Optional.of(usuario));
        Optional<Usuario> result = usuarioService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getNombre());
    }

    @Test
    public void testSaveUsuario() {
        Usuario usuario = new Usuario("John Doe", "john.doe@example.com",
                "secure123", true);
        Usuario savedUsuario = new Usuario(1L, "John Doe", "john.doe@example.com",
                "secure123", true);
        Mockito.when(usuarioDao.save(usuario)).thenReturn(savedUsuario);
        Usuario result = usuarioService.save(usuario);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getNombre());
    }

    @Test
    public void testDeletedById() {
        Mockito.doNothing().when(usuarioDao).deleteById(1L);
        usuarioService.deleteById(1L);
        Mockito.verify(usuarioDao, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testFindByName() {
        List<Usuario> usuarios = Arrays.asList(
                new Usuario(1L, "John Doe", "john.doe@example.com", "secure123",
                        true)
        );
        Mockito.when(usuarioDao.findByNombre("John Doe")).thenReturn(usuarios);
        Optional<List<Usuario>> result = usuarioService.findByNombre("John Doe");
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }

    @Test
    public void testFindByEmail() {
        Usuario usuario = new Usuario(1L, "John Doe", "john.doe@example.com", "secure123",
                true);
        Mockito.when(usuarioDao.findByEmail("john.doe@example.com")).thenReturn(Optional.of(usuario));
        Optional<Usuario> result = usuarioService.findByEmail("john.doe@example.com");
        assertTrue(result.isPresent());
        assertEquals("john.doe@example.com", result.get().getEmail());
    }

    @Test
    public void testFindByFechaDeCreacion() {
        LocalDateTime date = LocalDateTime.now();
        List<Usuario> usuarios = Arrays.asList(
            new Usuario(1L, "John Doe", "john.doe@example.com", "secure123",
                    true)
        );
        usuarios.get(0).setFechaCreacion(date);
        Mockito.when(usuarioDao.findByFechaCreacion(date)).thenReturn(usuarios);
        List<Usuario> result = usuarioService.findByFechaCreacion(date);
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
