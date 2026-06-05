package com.biblioteca.repository;

import java.util.List;

import com.biblioteca.entity.Loan;

public interface LoanRepository {

    void save(Loan loan);

    void update(Loan loan);

    void delete(int idLoan);

    Loan findById(int idLoan);

    List<Loan> findAll();

    List<Loan> findByState(String state);          // "active", "returned", "overdue"

    List<Loan> findByUserId(int idUser);           // Préstamos de un usuario específico

    List<Loan> findByBookId(int idBook);           // Historial de un libro

    List<Loan> findActiveLoans();                  // Préstamos activos (state = "active")

    boolean hasActiveLoansByUser(int idUser);      // Me permite saber si un usuario tiene prestamos sin devolver
}
