package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Books;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IBookService {
    public List<Books> findAll();
    public Optional<Books> findById(Long id);
    public Books save(@Valid Books books);
    public void deleteById(Long id);
    public List<Books> findByTitle(String title);
    public List<Books> findByPublishDateAfter(LocalDate publishDate);
    public Optional<Books> findByIsbn(String isbn);
    public List<Books> getByPopularity();
}
