package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Prestamo;
import com.example.DigitalLibraryStore.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRegisterUser() {
        Usuario usuario = new Usuario(1L, "John Doe", "john.doe@example.com", "secure123",
                true);
        HttpEntity<Usuario> request = new HttpEntity<>(usuario);
        ResponseEntity<Usuario> response = restTemplate.postForEntity("/api/v1/usuarios", request, Usuario.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuario.getNombre(), response.getBody().getNombre());
    }
}
