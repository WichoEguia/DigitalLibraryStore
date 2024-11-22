# UsersController Class Documentation

The `UsersController` class is a REST controller in a Spring Boot application that provides endpoints for managing users in a digital library system. It exposes various functionalities related to user management, such as retrieving, creating, updating, and deleting users, as well as performing specific searches and retrieving user-related loans.

## Class Overview

### Package
`com.example.DigitalLibraryStore.controllers`

### Dependencies
The class depends on the following:
- **`UserServiceImpl`**: Handles the business logic related to user management.
- **`LoanServiceImpl`**: Handles the business logic related to loans associated with users.

### Annotations
- **`@RestController`**: Marks the class as a REST controller, which handles HTTP requests.
- **`@RequestMapping("/api/v1/users")`**: Sets the base URL for all endpoints in this controller.

---

## Constructor
The constructor uses **dependency injection** to initialize `UserServiceImpl` and `LoanServiceImpl`.

```java
@Autowired
public UsersController(UserServiceImpl userService, LoanServiceImpl prestamoService) {
    this.userService = userService;
    this.prestamoService = prestamoService;
}
