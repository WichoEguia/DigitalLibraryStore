package com.example.DigitalLibraryStore.controllers;

import com.example.DigitalLibraryStore.dto.UserDto;
import com.example.DigitalLibraryStore.entities.Loan;
import com.example.DigitalLibraryStore.entities.User;
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

/**
 * REST controller for managing users in the digital library system.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserServiceImpl userService;
    private final LoanServiceImpl loanService;

    /**
     * Constructor for dependency injection of services.
     *
     * @param userService     The service for managing user records.
     * @param loanService The service for managing loan records.
     */
    @Autowired
    public UsersController(UserServiceImpl userService, LoanServiceImpl loanService) {
        this.userService = userService;
        this.loanService = loanService;
    }

    /**
     * Retrieves all users from the system.
     *
     * @return A list of all users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id The ID of the user.
     * @return The user details if found, or a 404 response if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new user.
     *
     * @param userDto The user details to create.
     * @return The created user with a 201 status code.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        User user = new User(userDto);
        User created = userService.save(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Updates an existing user's details.
     *
     * @param id         The ID of the user to update.
     * @param userDto    The updated user details.
     * @return The updated user if found, or a 404 response if not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setName(userDto.name());
            updatedUser.setEmail(userDto.email());
            updatedUser.setPassword(userDto.password());
            updatedUser.setEnabled(userDto.enabled());
            userService.save(updatedUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id The ID of the user to delete.
     * @return A 204 response if the user was deleted, or a 404 response if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.findById(id).isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Searches for users by their name.
     *
     * @param name The name to search for.
     * @return A list of users matching the name, or a 404 response if none are found.
     */
    @GetMapping("/searchByName")
    public ResponseEntity<List<User>> findByName(@RequestParam String name) {
        Optional<List<User>> users = userService.findByName(name);
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Searches for a user by their email.
     *
     * @param email The email to search for.
     * @return The user matching the email, or a 404 response if not found.
     */
    @GetMapping("/searchByEmail")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Searches for users created on a specific date.
     *
     * @param fechaCreacion The creation date to search for.
     * @return A list of users created on the specified date.
     */
    @GetMapping("/searchByFechaCreacion")
    public ResponseEntity<List<User>> findByFechaCreacion(@RequestParam LocalDateTime fechaCreacion) {
        List<User> users = userService.findByCreationDate(fechaCreacion);
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves all loans associated with a specific user.
     *
     * @param id The ID of the user.
     * @return A list of loans for the user.
     */
    @GetMapping("/getLoans/{id}")
    public ResponseEntity<List<Loan>> getLoans(@PathVariable Long id) {
        List<Loan> prestamos = loanService.getLoanByUserId(id);
        return ResponseEntity.ok(prestamos);
    }
}
