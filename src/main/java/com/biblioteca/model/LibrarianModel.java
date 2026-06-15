package com.biblioteca.model;

import java.util.List;

import com.biblioteca.entity.Librarian;
import com.biblioteca.repository.LibrarianRepository;

public class LibrarianModel {

    private final LibrarianRepository repository;

    public LibrarianModel(LibrarianRepository repository) {
        this.repository = repository;
    }

    public List<Librarian> getAllLibrarians() {
        return repository.findAll();
    }

    public void saveLibrarian(Librarian librarian) {
        repository.save(librarian);
    }

    public void updateLibrarian(Librarian librarian) {
        repository.update(librarian);
    }

    public void deleteLibrarian(String email) {
        repository.delete(email);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public Librarian findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
