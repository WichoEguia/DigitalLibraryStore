package com.example.DigitalLibraryStore.entities;

import com.example.DigitalLibraryStore.utils.annotations.MinPages;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Books {
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
    private String format;

    @Column(nullable = false)
    private LocalDate publishDate;

    @Column
    @MinPages(value = 100)
    private Integer pagesNo;

    @Column(nullable = false)
    @Min(value = 0, message = "La popularidad no puede ser menor que 0.")
    @Max(value = 10, message = "La popularidad no puede ser mayor que 100.")
    private double popularity;

    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Loans> loans;

    public Books(String isbn, String title, String description, String imageUrl, String format,
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
}
