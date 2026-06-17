package com.biblioteca.model;

import java.util.List;

import com.biblioteca.entity.Book;
import com.biblioteca.entity.Loan;
import com.biblioteca.entity.User;
import com.biblioteca.repository.BookRepository;
import com.biblioteca.repository.LoanRepository;
import com.biblioteca.repository.UserRepository;

public class UserLoanModel {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public UserLoanModel(LoanRepository loanRepository,
            BookRepository bookRepository,
            UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // Todos los libros disponibles
    public List<Book> getAvailableBooks() {
        return bookRepository.findByState("available");
    }

    // Todos los libros del catálogo
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Préstamos activos de un usuario específico
    public List<Loan> getMyActiveLoans(String email) {
        return loanRepository.findActiveLoansByUserEmail(email);
    }

    // Verificar disponibilidad de un libro
    public Book getBookById(int idBook) {
        return bookRepository.findById(idBook);
    }

    // Obtener usuario por email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Solicitar préstamo
    public void requestLoan(int idUser, int idBook) {
        Loan loan = new Loan();
        loan.setIdUser(idUser);
        loan.setIdBook(idBook);
        loanRepository.registerLoan(loan);
    }

    // Devolver libro
    public void returnBook(int idLoan) {
        loanRepository.registerReturn(idLoan);
    }
}
