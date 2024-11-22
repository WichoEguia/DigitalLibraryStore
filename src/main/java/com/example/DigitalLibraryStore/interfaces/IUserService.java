package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
    Optional<List<User>> findByName(String name);
    Optional<User> findByEmail(String email);
    List<User> findByCreationDate(LocalDateTime createdDate);
}
