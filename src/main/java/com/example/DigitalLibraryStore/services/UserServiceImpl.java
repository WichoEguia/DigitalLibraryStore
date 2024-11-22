package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Users;
import com.example.DigitalLibraryStore.interfaces.IUserService;
import com.example.DigitalLibraryStore.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the IUserService interface that handles CRUD operations
 * for managing users, using the UserDao repository.
 */
@Service
public class UserServiceImpl implements IUserService {

    private final UserDao userDao;

    /**
     * Constructor for UserServiceImpl that accepts a UserDao instance for dependency injection.
     *
     * @param userDao The DAO for interacting with user data.
     */
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Retrieves all users from the data source.
     *
     * @return A list of all users.
     */
    @Override
    public List<Users> findAll() {
        return userDao.findAll();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id The ID of the user.
     * @return An Optional containing the user if found, or an empty Optional if not found.
     */
    @Override
    public Optional<Users> findById(Long id) {
        return userDao.findById(id);
    }

    /**
     * Saves a new or updated user to the data source.
     *
     * @param users The user to save.
     * @return The saved user.
     */
    @Override
    public Users save(Users users) {
        return userDao.save(users);
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id The ID of the user to delete.
     */
    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    /**
     * Retrieves a list of users by their name.
     *
     * @param name The name of the users to search for.
     * @return An Optional containing a list of users with the specified name, or an empty Optional if none found.
     */
    @Override
    public Optional<List<Users>> findByName(String name) {
        return Optional.ofNullable(userDao.findByName(name));
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user to search for.
     * @return An Optional containing the user if found, or an empty Optional if not found.
     */
    @Override
    public Optional<Users> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * Retrieves a list of users who were created on a specific date.
     *
     * @param createdDate The creation date of the users to search for.
     * @return A list of users created on the specified date.
     */
    @Override
    public List<Users> findByCreationDate(LocalDateTime createdDate) {
        return userDao.findByCreationDate(createdDate);
    }
}
