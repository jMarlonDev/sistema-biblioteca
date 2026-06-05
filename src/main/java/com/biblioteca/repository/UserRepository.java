package com.biblioteca.repository;

import java.util.List;

import com.biblioteca.entity.User;

public interface UserRepository {

    void save(User user);

    void update(User user);

    void delete(String identification);

    User findByIdentification(String identification);

    User findByEmail(String email);

    List<User> findAll();

    boolean existsByIdentification(String identification);

    boolean existsByEmail(String email);
}
