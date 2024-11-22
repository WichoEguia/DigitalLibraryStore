package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Users;
import com.example.DigitalLibraryStore.repositories.UserDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsersServiceImplTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testFindAll() {
        List<Users> users = Arrays.asList(
            new Users(1L, "John Doe", "john.doe@example.com", "secure123",
                    true),
            new Users(2L,"Jane Doe", "jane.doe@example.com", "secure321",
                    true)
        );
        Mockito.when(userDao.findAll()).thenReturn(users);
        List<Users> result = userService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void findById() {
        Users users = new Users(1L, "John Doe", "john.doe@example.com", "secure123",
                true);
        Mockito.when(userDao.findById(1L)).thenReturn(Optional.of(users));
        Optional<Users> result = userService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testSaveuser() {
        Users users = new Users("John Doe", "john.doe@example.com",
                "secure123", true);
        Users savedUsers = new Users(1L, "John Doe", "john.doe@example.com",
                "secure123", true);
        Mockito.when(userDao.save(users)).thenReturn(savedUsers);
        Users result = userService.save(users);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testDeletedById() {
        Mockito.doNothing().when(userDao).deleteById(1L);
        userService.deleteById(1L);
        Mockito.verify(userDao, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testFindByName() {
        List<Users> users = Arrays.asList(
                new Users(1L, "John Doe", "john.doe@example.com", "secure123",
                        true)
        );
        Mockito.when(userDao.findByName("John Doe")).thenReturn(users);
        Optional<List<Users>> result = userService.findByName("John Doe");
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }

    @Test
    public void testFindByEmail() {
        Users users = new Users(1L, "John Doe", "john.doe@example.com", "secure123",
                true);
        Mockito.when(userDao.findByEmail("john.doe@example.com")).thenReturn(Optional.of(users));
        Optional<Users> result = userService.findByEmail("john.doe@example.com");
        assertTrue(result.isPresent());
        assertEquals("john.doe@example.com", result.get().getEmail());
    }

    @Test
    public void testFindByFechaDeCreacion() {
        LocalDateTime date = LocalDateTime.now();
        List<Users> users = Arrays.asList(
            new Users(1L, "John Doe", "john.doe@example.com", "secure123",
                    true)
        );
        users.get(0).setCreationDate(date);
        Mockito.when(userDao.findByCreationDate(date)).thenReturn(users);
        List<Users> result = userService.findByCreationDate(date);
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
