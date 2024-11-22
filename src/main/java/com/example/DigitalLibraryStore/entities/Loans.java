package com.example.DigitalLibraryStore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "${entity.loans.table.name}")
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
    private Users user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books book;

    @Column(nullable = false)
    private LocalDateTime loanDate;

    @Column(nullable = false)
    private LocalDateTime devolutionDate;

    public Loans(Users user, Books book, LocalDateTime devolutionDate) {
        this.user = user;
        this.book = book;
        this.loanDate = LocalDateTime.now();
        this.devolutionDate = devolutionDate;
    }
}
