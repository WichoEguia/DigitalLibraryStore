package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    List<User> findByName(String Name);
    Optional<User> findByEmail(String email);
    List<User> findByCreationDate(LocalDateTime createdDate);
}
