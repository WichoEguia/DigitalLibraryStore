package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.User;
import com.example.DigitalLibraryStore.repositories.UserDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testFindAll() {
        List<User> users = Arrays.asList(
                new User(1L, "John Doe", "john.doe@example.com", "secure123", true),
                new User(2L, "Jane Doe", "jane.doe@example.com", "secure321", true)
        );
        Mockito.when(userDao.findAll()).thenReturn(users);
        List<User> result = userService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        User user = new User(1L, "John Doe", "john.doe@example.com", "secure123", true);
        Mockito.when(userDao.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> result = userService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testFindById_NotFound() {
        Mockito.when(userDao.findById(1L)).thenReturn(Optional.empty());
        Optional<User> result = userService.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    public void testSaveUser() {
        User user = new User("John Doe", "john.doe@example.com", "secure123", true);
        User savedUser = new User(1L, "John Doe :)", "john.doe@example.com", "secure123", true);
        Mockito.when(userDao.save(user)).thenReturn(savedUser);

        User result = userService.save(user);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe :)", result.getName());
    }

    @Test
    public void testDeleteById() {
        Mockito.doNothing().when(userDao).deleteById(1L);
        userService.deleteById(1L);
        Mockito.verify(userDao, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteById_NotFound() {
        Mockito.doNothing().when(userDao).deleteById(999L);
        userService.deleteById(999L);
        Mockito.verify(userDao, Mockito.times(1)).deleteById(999L);
    }

    @Test
    public void testFindByName() {
        List<User> users = Arrays.asList(
                new User(1L, "John Doe", "john.doe@example.com", "secure123", true)
        );
        Mockito.when(userDao.findByName("John Doe")).thenReturn(users);
        Optional<List<User>> result = userService.findByName("John Doe");
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }

    @Test
    public void testFindByName_NotFound() {
        Mockito.when(userDao.findByName("Unknown User")).thenReturn(null);
        Optional<List<User>> result = userService.findByName("Unknown User");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmail() {
        User user = new User(1L, "John Doe", "john.doe@example.com", "secure123", true);
        Mockito.when(userDao.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByEmail("john.doe@example.com");
        assertTrue(result.isPresent());
        assertEquals("john.doe@example.com", result.get().getEmail());
    }

    @Test
    public void testFindByEmail_NotFound() {
        Mockito.when(userDao.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());
        Optional<User> result = userService.findByEmail("nonexistent@example.com");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByCreationDate() {
        LocalDateTime date = LocalDateTime.now();
        List<User> users = Arrays.asList(
                new User(1L, "John Doe", "john.doe@example.com", "secure123", true)
        );
        users.get(0).setCreationDate(date);
        Mockito.when(userDao.findByCreationDate(date)).thenReturn(users);

        List<User> result = userService.findByCreationDate(date);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindByCreationDate_NoResults() {
        LocalDateTime date = LocalDateTime.now().minusDays(1);
        Mockito.when(userDao.findByCreationDate(date)).thenReturn(Arrays.asList());

        List<User> result = userService.findByCreationDate(date);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUserWithNoLoans() {
        User user = new User(1L, "John NoLoans", "john.noloans@example.com", "password", true);
        Mockito.when(userDao.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(0, result.get().getLoans().size()); // Ensure no loans are associated with this user
    }
}
