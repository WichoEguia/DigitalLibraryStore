package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRegisterUser() {
        Users users = new Users("John Doe", "john.doe@example.com", "secure123", true);
        HttpEntity<Users> request = new HttpEntity<>(users);
        ResponseEntity<Users> response = restTemplate.postForEntity("/api/v1/users", request, Users.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(users.getName(), response.getBody().getName());
    }
}
