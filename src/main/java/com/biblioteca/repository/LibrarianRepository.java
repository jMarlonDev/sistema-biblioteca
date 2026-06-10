package com.biblioteca.repository;

import java.util.List;

import com.biblioteca.entity.Librarian;

public interface LibrarianRepository {

    void save(Librarian librarian);

    void update(Librarian librarian);

    void delete(String email);

    Librarian findByEmail(String email);

    Librarian findById(int id);

    List<Librarian> findAll();

    boolean existsByEmail(String email);
}
