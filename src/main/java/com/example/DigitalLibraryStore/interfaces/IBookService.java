package com.example.DigitalLibraryStore.interfaces;

import com.example.DigitalLibraryStore.entities.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book bookDto);
    void deleteById(Long id);
    List<Book> findByTitle(String title);
    List<Book> findByPublishDateAfter(LocalDate publishDate);
    Optional<Book> findByIsbn(String isbn);
    List<Book> getByPopularity();
}
