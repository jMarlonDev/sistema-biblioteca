package com.biblioteca.model;

import java.util.List;

import com.biblioteca.entity.User;
import com.biblioteca.repository.UserRepository;

public class UserModel {

    private final UserRepository repository;

    public UserModel(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public void updateUser(User user) {
        repository.update(user);
    }

    public void deleteUser(String email) {
        repository.delete(email);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
