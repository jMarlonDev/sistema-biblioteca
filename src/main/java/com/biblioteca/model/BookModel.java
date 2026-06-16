package com.biblioteca.model;

import java.util.List;

import com.biblioteca.entity.Book;
import com.biblioteca.repository.BookRepository;

public class BookModel {

    private final BookRepository repository;

    public BookModel(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public void saveBook(Book book) {
        repository.save(book);
    }

    public void updateBook(Book book) {
        repository.update(book);
    }

    public void deleteBook(int idBook) {
        repository.delete(idBook);
    }

    public boolean existsById(int idBook) {
        return repository.existsById(idBook);
    }

    public Book findById(int idBook) {
        return repository.findById(idBook);
    }
}
