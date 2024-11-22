package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Books;
import com.example.DigitalLibraryStore.interfaces.IBookService;
import com.example.DigitalLibraryStore.repositories.BookDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class BookServiceImpl implements IBookService {
    private BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Books> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Optional<Books> findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public Books save(@Valid Books books) {
        return bookDao.save(books);
    }

    @Override
    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<Books> findByTitle(String title) {
        return bookDao.findByTitle(title);
    }

    @Override
    public List<Books> findByPublishDateAfter(LocalDate publishDate) {
        return bookDao.findByPublishDate(publishDate);
    }

    @Override
    public Optional<Books> findByIsbn(String isbn) {
        return Optional.ofNullable(bookDao.findByIsbn(isbn));
    }

    @Override
    public List<Books> getByPopularity() {
        return bookDao.findAll(Sort.by(Sort.Direction.DESC, "popularidad"));
    }
}
