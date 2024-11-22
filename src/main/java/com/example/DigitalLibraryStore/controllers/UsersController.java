package com.example.DigitalLibraryStore.controllers;

import com.example.DigitalLibraryStore.entities.Loans;
import com.example.DigitalLibraryStore.entities.Users;
import com.example.DigitalLibraryStore.services.LoanServiceImpl;
import com.example.DigitalLibraryStore.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UserServiceImpl userService;
    private final LoanServiceImpl prestamoService;

    @Autowired
    public UsersController(UserServiceImpl userService, LoanServiceImpl prestamoService) {
        this.userService = userService;
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users users) {
        Users created = userService.save(users);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @Valid @RequestBody Users usersDetails) {
        Optional<Users> user = userService.findById(id);
        if (user.isPresent()) {
            Users updatedUsers = user.get();
            updatedUsers.setName(usersDetails.getName());
            updatedUsers.setEmail(usersDetails.getEmail());
            updatedUsers.setPassword(usersDetails.getPassword());
            updatedUsers.setEnabled(usersDetails.isEnabled());
            userService.save(updatedUsers);
            return ResponseEntity.ok(updatedUsers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.findById(id).isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Users>> findByName(@RequestParam String name) {
        Optional<List<Users>> users = userService.findByName(name);
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity<Users> findByEmail(@RequestParam String email) {
        Optional<Users> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByFechaCreacion")
    public ResponseEntity<List<Users>> findByFechaCreacion(@RequestParam LocalDateTime fechaCreacion) {
        List<Users> users = userService.findByCreationDate(fechaCreacion);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getLoans/{id}")
    public ResponseEntity<List<Loans>> getLoans(@PathVariable Long id) {
        List<Loans> prestamos = prestamoService.getLoanByUserId(id);
        return ResponseEntity.ok(prestamos);
    }
}
