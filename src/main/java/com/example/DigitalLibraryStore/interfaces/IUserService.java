package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Users;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<Users> findAll();
    public Optional<Users> findById(Long id);
    public Users save(Users users);
    public void deleteById(Long id);
    public Optional<List<Users>> findByName(String name);
    public Optional<Users> findByEmail(String email);
    public List<Users> findByCreationDate(LocalDateTime createdDate);
}
