package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Loan;

import java.time.LocalDateTime;
import java.util.List;

public interface ILoanService {
    Loan createLoan(Long userId, String isbn, LocalDateTime devolutionDate);
    List<Loan> getLoanByUserId(Long userId);
}
