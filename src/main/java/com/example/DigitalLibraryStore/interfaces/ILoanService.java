package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Loans;

import java.util.List;

public interface ILoanService {
    List<Loans> getLoanByUserId(Long userId);
}
