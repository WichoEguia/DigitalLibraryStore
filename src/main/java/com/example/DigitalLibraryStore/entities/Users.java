package com.example.DigitalLibraryStore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "${entity.users.table.name}")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "${message.users.name.blank_name}")
    private String name;

    @Column(nullable = false)
    @Email(message = "${message.users.email.invalid_format}")
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(updatable = false)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Loans> loans;

    public Users(String name, String email, String password, boolean enabled) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.creationDate = LocalDateTime.now();
        this.loans = new HashSet<>();
    }

    public Users(Long id, String name, String email, String password, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.creationDate = LocalDateTime.now();
        this.loans = new HashSet<>();
    }
}
