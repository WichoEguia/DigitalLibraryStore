package com.example.DigitalLibraryStore.entities;

import com.example.DigitalLibraryStore.dto.BookDto;
import com.example.DigitalLibraryStore.utils.enums.Format;
import com.example.DigitalLibraryStore.utils.annotations.MinPages;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "${entity.books.table.name}")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column
    private String imageUrl;

    @Column
    private Format format;

    @Column(nullable = false)
    private LocalDate publishDate;

    @Column
    @MinPages(value = 100, message = "${message.books.pagesNo.invalid_pages_number}")
    private Integer pagesNo;

    @Column(nullable = false)
    @Min(value = 0, message = "${message.books.popularity.invalid_min}")
    @Max(value = 10, message = "${message.books.popularity.invalid_min}")
    private double popularity;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Loan> loans;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String isbn, String title, String description, String imageUrl, Format format,
                LocalDate publishDate, Integer pagesNo, double popularity) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.format = format;
        this.publishDate = publishDate;
        this.pagesNo = pagesNo;
        this.popularity = popularity;
    }

    public Book(BookDto bookDto) {
        this.isbn = bookDto.isbn();
        this.title = bookDto.title();
        this.description = bookDto.description();
        this.imageUrl = bookDto.imageUrl();
        this.format = bookDto.format();
        this.publishDate = bookDto.publishDate();
        this.pagesNo = bookDto.pagesNo();
        this.popularity = bookDto.popularity();
    }
}
