# Project Purpose

This project aims to evaluate a person's technical skills by implementing a library management system using **Spring Boot**. The system should allow the management of **Users**, **Books**, and **Loans**, testing knowledge of backend development, application architecture, and modern tools and technologies.

## Main Components

### 1. Key Entities
- **User**: Represents the registered users in the system. Each user has basic attributes like name, email, password, and a status indicating if the user is active.
- **Book**: Represents the book available in the library. Each book includes details such as title, author, genre, and availability.
- **Loan**: Manages the loans made by users. It includes information about the borrowed book, the user requesting it, the loan date, and the return date.

### 2. Core Functionalities
- **User Management**:
    - Register and edit users.
    - Activate and deactivate user accounts.
- **Book Management**:
    - Add new book to the system.
    - Update information for existing book.
    - Check book availability.
- **Loan Management**:
    - Register book loans and returns.
    - Validate that a book is available before processing a loan.
    - View a user's loan history.

### 3. Use of Spring Boot
The project uses **Spring Boot** as the foundation for implementation, enabling the system to be modular and scalable. Key modules include:
- **Spring Data JPA**: For database interactions.
- **Spring MVC**: For creating RESTful controllers that expose the system's functionalities.
- **H2 (In-Memory Database)**: For quick development and testing purposes.

---

## Summary
This project not only evaluates the developer's technical knowledge in technologies like **Spring Boot** and **Spring Data JPA** but also their ability to implement a functional system that efficiently manages library operations. The evaluation focuses on the quality of the code, architecture design, proper implementation of required functionalities, and the creation of tests to validate the system.
