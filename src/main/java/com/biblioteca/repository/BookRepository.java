package com.biblioteca.repository;

import java.util.List;

import com.biblioteca.entity.Book;

public interface BookRepository {

    void save(Book book);

    void update(Book book);

    void delete(int idBook);

    Book findById(int idBook);

    List<Book> findAll();

    List<Book> findByState(String state);        // "available" o "loaned"

    boolean existsById(int idBook);
}
