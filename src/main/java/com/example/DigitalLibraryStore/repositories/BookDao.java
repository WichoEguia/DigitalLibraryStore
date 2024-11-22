package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByPublishDate(LocalDate publishDate);
    Book findByIsbn(String isbn);
}
