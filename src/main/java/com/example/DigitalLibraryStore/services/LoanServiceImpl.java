package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Book;
import com.example.DigitalLibraryStore.entities.Loan;
import com.example.DigitalLibraryStore.entities.User;
import com.example.DigitalLibraryStore.interfaces.ILoanService;
import com.example.DigitalLibraryStore.repositories.BookDao;
import com.example.DigitalLibraryStore.repositories.LoanDao;
import com.example.DigitalLibraryStore.repositories.UserDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the ILoanService interface that handles operations related
 * to managing loans using the LoanDao repository.
 */
@Service
public class LoanServiceImpl implements ILoanService {

    private final LoanDao loanDao;
    private final UserDao userDao;
    private final BookDao bookDao;

    /**
     * Constructor for LoanServiceImpl that accepts a LoanDao instance for dependency injection.
     *
     * @param loanDao The DAO for interacting with loan data.
     * @param userDao The DAO for interacting with user data.
     * @param bookDao The DAO for interacting with book data.
     */
    @Autowired
    public LoanServiceImpl(LoanDao loanDao, BookDao bookDao, UserDao userDao) {
        this.loanDao = loanDao;
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    /**
     * Retrieves a list of loans associated with a specific user, based on the user's ID.
     *
     * @param userId The ID of the user for whom to retrieve the loans.
     * @return A list of loans associated with the given user.
     */
    @Transactional
    @Override
    public List<Loan> getLoanByUserId(Long userId) {
        return loanDao.findByUserId(userId);
    }

    /**
     * Create a new loan between a book and a user and specify its devolution date.
     *
     * @param userId            The ID of the user which will acquire the loan.
     * @param isbn              The ISBN of the book requested for the user.
     * @param devolutionDate    The date to return the book.
     */
    @Transactional
    @Override
    public Loan createLoan(Long userId, String isbn, LocalDateTime devolutionDate) {
        User user = userDao.getById(userId);
        Book book = bookDao.findByIsbn(isbn);
        Loan loan = new Loan(user, book, devolutionDate);
        return loanDao.save(loan);
    }
}
