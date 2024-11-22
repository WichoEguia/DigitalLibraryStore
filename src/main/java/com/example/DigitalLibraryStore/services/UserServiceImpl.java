package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.User;
import com.example.DigitalLibraryStore.interfaces.IUserService;
import com.example.DigitalLibraryStore.repositories.UserDao;
import jakarta.transaction.Transactional;
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
    @Transactional
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id The ID of the user.
     * @return An Optional containing the user if found, or an empty Optional if not found.
     */
    @Transactional
    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    /**
     * Saves a new or updated user to the data source.
     *
     * @param user The user to save.
     * @return The saved user.
     */
    @Transactional
    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id The ID of the user to delete.
     */
    @Transactional
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
    @Transactional
    @Override
    public Optional<List<User>> findByName(String name) {
        return Optional.ofNullable(userDao.findByName(name));
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user to search for.
     * @return An Optional containing the user if found, or an empty Optional if not found.
     */
    @Transactional
    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * Retrieves a list of users who were created on a specific date.
     *
     * @param createdDate The creation date of the users to search for.
     * @return A list of users created on the specified date.
     */
    @Transactional
    @Override
    public List<User> findByCreationDate(LocalDateTime createdDate) {
        return userDao.findByCreationDate(createdDate);
    }
}
