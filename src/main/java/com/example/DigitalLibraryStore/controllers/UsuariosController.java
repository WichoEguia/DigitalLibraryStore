package com.example.DigitalLibraryStore.controllers;

import com.example.DigitalLibraryStore.entities.Prestamo;
import com.example.DigitalLibraryStore.entities.Usuario;
import com.example.DigitalLibraryStore.services.PrestamoServiceImpl;
import com.example.DigitalLibraryStore.services.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {
    private final UsuarioServiceImpl usuarioService;
    private final PrestamoServiceImpl prestamoService;

    @Autowired
    public UsuariosController(UsuarioServiceImpl usuarioService, PrestamoServiceImpl prestamoService) {
        this.usuarioService = usuarioService;
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario created = usuarioService.save(usuario);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            Usuario updatedUsuario = usuario.get();
            updatedUsuario.setNombre(usuarioDetails.getNombre());
            updatedUsuario.setEmail(usuarioDetails.getEmail());
            updatedUsuario.setPassword(usuarioDetails.getPassword());
            updatedUsuario.setEnabled(usuarioDetails.isEnabled());
            usuarioService.save(updatedUsuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.findById(id).isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Usuario>> findByName(@RequestParam String name) {
        Optional<List<Usuario>> usuarios = usuarioService.findByNombre(name);
        return usuarios.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity<Usuario> findByEmail(@RequestParam String email) {
        Optional<Usuario> usuario = usuarioService.findByEmail(email);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByFechaCreacion")
    public ResponseEntity<List<Usuario>> findByFechaCreacion(@RequestParam LocalDateTime fechaCreacion) {
        List<Usuario> usuarios = usuarioService.findByFechaCreacion(fechaCreacion);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/getPrestamos/{id}")
    public ResponseEntity<List<Prestamo>> getPrestamos(@PathVariable Long id) {
        List<Prestamo> prestamos = prestamoService.getPrestamoByUsuarioId(id);
        return ResponseEntity.ok(prestamos);
    }
}
