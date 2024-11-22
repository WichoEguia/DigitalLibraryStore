package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Loans;
import com.example.DigitalLibraryStore.interfaces.ILoanService;
import com.example.DigitalLibraryStore.repositories.LoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the ILoanService interface that handles operations related
 * to managing loans using the LoanDao repository.
 */
@Service
public class LoanServiceImpl implements ILoanService {

    private final LoanDao loanDao;

    /**
     * Constructor for LoanServiceImpl that accepts a LoanDao instance for dependency injection.
     *
     * @param loanDao The DAO for interacting with loan data.
     */
    @Autowired
    public LoanServiceImpl(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    /**
     * Retrieves a list of loans associated with a specific user, based on the user's ID.
     *
     * @param userId The ID of the user for whom to retrieve the loans.
     * @return A list of loans associated with the given user.
     */
    @Override
    public List<Loans> getLoanByUserId(Long userId) {
        return loanDao.findByUserId(userId);
    }
}
