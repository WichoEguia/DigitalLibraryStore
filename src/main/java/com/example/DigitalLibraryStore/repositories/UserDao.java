package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<Users, Long> {
    List<Users> findByName(String Name);
    Optional<Users> findByEmail(String email);
    List<Users> findByCreationDate(LocalDateTime createdDate);
}
