package com.example.DigitalLibraryStore.repositories;

import com.example.DigitalLibraryStore.entities.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsersDaoTest {
    @Autowired
    private UserDao userDao;

    @AfterEach
    public void afterEach() {
        userDao.deleteAll();
    }

    private Users createUser() {
        Users users = new Users("John Doe", "john.doe@example.com",
                "secure123", true);
        return userDao.save(users);
    }

    @Test
    public void testSaveUser() {
        Users savedUsers = createUser();
        assertNotNull(savedUsers);
        assertEquals("John Doe", savedUsers.getName());
    }

    @Test
    public void testFindById() {
        Users savedUsers = createUser();
        Optional<Users> result = userDao.findById(savedUsers.getId());
        assertNotNull(savedUsers);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testFindByName() {
        createUser();
        List<Users> result = userDao.findByName("John Doe");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindByEmail() {
        createUser();
        Optional<Users> result = userDao.findByEmail("john.doe@example.com");
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }
}