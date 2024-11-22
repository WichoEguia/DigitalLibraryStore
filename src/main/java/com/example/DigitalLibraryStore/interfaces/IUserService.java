package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> findAll();
    public Optional<User> findById(Long id);
    public User save(User user);
    public void deleteById(Long id);
    public Optional<List<User>> findByName(String name);
    public Optional<User> findByEmail(String email);
    public List<User> findByCreationDate(LocalDateTime createdDate);
}
