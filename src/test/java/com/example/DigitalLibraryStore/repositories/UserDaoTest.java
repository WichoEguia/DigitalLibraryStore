package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @AfterEach
    public void afterEach() {
        userDao.deleteAll();
    }

    private User createUser() {
        User user = new User("John Doe", "john.doe@example.com",
                "secure123", true);
        return userDao.save(user);
    }

    @Test
    public void testSaveUser() {
        User savedUser = createUser();
        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());
    }

    @Test
    public void testFindById() {
        User savedUser = createUser();
        Optional<User> result = userDao.findById(savedUser.getId());
        assertNotNull(savedUser);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testFindByName() {
        createUser();
        List<User> result = userDao.findByName("John Doe");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindByEmail() {
        createUser();
        Optional<User> result = userDao.findByEmail("john.doe@example.com");
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }
}