package com.example.DigitalLibraryStore.integration;

import com.example.DigitalLibraryStore.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRegisterUser() {
        User user = new User("John Doe", "john.doe@example.com", "secure123", true);
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.postForEntity("/api/v1/users", request, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user.getName(), response.getBody().getName());
    }
}
