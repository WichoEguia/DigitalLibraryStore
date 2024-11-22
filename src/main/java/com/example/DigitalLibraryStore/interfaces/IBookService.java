package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IBookService {
    public List<Book> findAll();
    public Optional<Book> findById(Long id);
    public Book save(Book bookDto);
    public void deleteById(Long id);
    public List<Book> findByTitle(String title);
    public List<Book> findByPublishDateAfter(LocalDate publishDate);
    public Optional<Book> findByIsbn(String isbn);
    public List<Book> getByPopularity();
}
