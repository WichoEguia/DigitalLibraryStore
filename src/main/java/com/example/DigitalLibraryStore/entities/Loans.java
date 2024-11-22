package com.example.DigitalLibraryStore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prestamo")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books books;

    @Column(nullable = false)
    private LocalDateTime loanDate;

    @Column(nullable = false)
    private LocalDateTime devolutionDate;

    public Loans(Users users, Books books, LocalDateTime devolutionDate) {
        this.users = users;
        this.books = books;
        this.loanDate = LocalDateTime.now();
        this.devolutionDate = devolutionDate;
    }
}
