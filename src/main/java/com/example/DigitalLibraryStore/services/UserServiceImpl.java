package com.example.DigitalLibraryStore.services;

import com.example.DigitalLibraryStore.entities.Users;
import com.example.DigitalLibraryStore.interfaces.IUserService;
import com.example.DigitalLibraryStore.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Users> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Users save(Users users) {
        return userDao.save(users);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public Optional<List<Users>> findByName(String name) {
        return Optional.ofNullable(userDao.findByName(name));
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<Users> findByCreationDate(LocalDateTime createdDate) {
        return userDao.findByCreationDate(createdDate);
    }
}
