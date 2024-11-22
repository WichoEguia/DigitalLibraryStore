package com.example.DigitalLibraryStore.controllers;

import com.example.DigitalLibraryStore.entities.Loan;
import com.example.DigitalLibraryStore.services.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * REST controller for managing loans in the digital library system.
 */
@RestController
@RequestMapping("/api/v1/loans")
public class LoansController {
    private final LoanServiceImpl loanService;

    /**
     * Constructor for dependency injection of services.
     *
     * @param loanService The service for managing loan records.
     */
    @Autowired
    public LoansController(LoanServiceImpl loanService) {
        this.loanService = loanService;
    }

    /**
     * Creates a new loan.
     *
     * @param userId            The ID of the user which will acquire the loan.
     * @param isbn              The ISBN of the book requested for the user.
     * @param devolutionDate    The date to return the book.
     * @return The created user with a 201 status code.
     */
    @PostMapping
    public ResponseEntity<Loan> createLoan(@PathVariable Long userId, @PathVariable String isbn,
                                           @PathVariable LocalDateTime devolutionDate) {
        Loan loan = loanService.createLoan(userId, isbn, devolutionDate);
        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }
}
