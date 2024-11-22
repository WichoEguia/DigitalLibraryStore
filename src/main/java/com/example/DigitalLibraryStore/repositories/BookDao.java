package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookDao extends JpaRepository<Books, Long> {
    List<Books> findByTitle(String title);
    List<Books> findByPublishDate(LocalDate publishDate);
    Books findByIsbn(String isbn);
}
