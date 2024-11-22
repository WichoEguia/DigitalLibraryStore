package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Loans;
import com.example.DigitalLibraryStore.interfaces.ILoanService;
import com.example.DigitalLibraryStore.repositories.LoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements ILoanService {
    private LoanDao loanDao;

    @Autowired
    public LoanServiceImpl(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    @Override
    public List<Loans> getLoanByUserId(Long userId) {
        return loanDao.findByUserId(userId);
    }
}
