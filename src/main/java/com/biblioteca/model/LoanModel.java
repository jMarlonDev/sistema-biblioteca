package com.biblioteca.model;

import java.util.List;

import com.biblioteca.entity.Book;
import com.biblioteca.entity.Loan;
import com.biblioteca.entity.User;
import com.biblioteca.repository.BookRepository;
import com.biblioteca.repository.LoanRepository;
import com.biblioteca.repository.UserRepository;

public class LoanModel {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanModel(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByState("available");
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.findActiveLoans();
    }

    public Book getBookById(int idBook) {
        return bookRepository.findById(idBook);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void checkoutLoan(Loan loan) {
        loanRepository.registerLoan(loan);
    }

    public void processReturn(int idLoan) {
        loanRepository.registerReturn(idLoan);
    }
}
