package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanDao extends JpaRepository<Loans, Long> {
    @Query(value = "SELECT * FROM loan AS p WHERE p.user_id = :userId", nativeQuery = true)
    List<Loans> findByUserId(@Param("userId") Long userId);
}
