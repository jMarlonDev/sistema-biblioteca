package com.biblioteca.repository;

import java.util.List;

import com.biblioteca.entity.Librarian;

public interface LibrarianRepository {

    void save(Librarian librarian);

    void update(Librarian librarian);

    void delete(String identification);

    Librarian findByIdentification(String identification);

    Librarian findByEmail(String email);

    List<Librarian> findAll();

    boolean existsByIdentification(String identification);

    boolean existsByEmail(String email);
}
