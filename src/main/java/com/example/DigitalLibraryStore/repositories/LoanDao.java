package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanDao extends JpaRepository<Loan, Long> {
    @Query(value = "SELECT * FROM loan AS p WHERE p.user_id = :userId", nativeQuery = true)
    List<Loan> findByUserId(@Param("userId") Long userId);
}
