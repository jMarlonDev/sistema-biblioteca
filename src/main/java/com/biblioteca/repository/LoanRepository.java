package com.biblioteca.repository;

import java.util.List;

import com.biblioteca.entity.Loan;

public interface LoanRepository {

    void registerLoan(Loan loan);

    void registerReturn(int idLoan);

    List<Loan> findActiveLoans();
}
