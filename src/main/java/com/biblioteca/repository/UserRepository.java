package com.biblioteca.repository;

import java.util.List;

import com.biblioteca.entity.User;

public interface UserRepository {

    void save(User user);

    void update(User user);

    void delete(String email);

    User findByEmail(String email);

    User findById(int id);

    List<User> findAll();

    boolean existsByEmail(String email);

    boolean hasActiveLoans(String email); // ← nuevo
}
