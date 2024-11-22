package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Loans;

import java.time.LocalDateTime;
import java.util.List;

public interface ILoanService {
    Loans createLoan(Long userId, String isbn, LocalDateTime devolutionDate);
    List<Loans> getLoanByUserId(Long userId);
}
