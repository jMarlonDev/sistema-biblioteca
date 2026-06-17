package com.biblioteca.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.biblioteca.entity.Book;
import com.biblioteca.entity.Loan;
import com.biblioteca.entity.User;
import com.biblioteca.model.UserLoanModel;
import com.biblioteca.view.BookAvailabilityView;
import com.biblioteca.view.UserLoanView;
import com.biblioteca.view.UserReturnView;

public class UserLoanController {

    private final BookAvailabilityView availabilityView;
    private final UserLoanView loanView;
    private final UserReturnView returnView;
    private final UserLoanModel model;
    private String userEmail; // ya no es final, se puede actualizar

    public UserLoanController(BookAvailabilityView availabilityView,
            UserLoanView loanView,
            UserReturnView returnView,
            UserLoanModel model,
            String userEmail) {
        this.availabilityView = availabilityView;
        this.loanView = loanView;
        this.returnView = returnView;
        this.model = model;
        this.userEmail = userEmail;

        // Pre-rellenar el campo de email en la vista
        loanView.setUserEmail(userEmail);

        initAvailabilityListeners();
        initLoanListeners();
        initReturnListeners();

        loadAvailableBooks();
        loadMyLoans();
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
        loanView.setUserEmail(email);
        loadAvailableBooks();
        loadMyLoans();
    }

    // ── Availability ──────────────────────────────────────────
    private void initAvailabilityListeners() {
        availabilityView.getBtnShowAll().addActionListener(e -> loadAllBooks());
        availabilityView.getBtnSearch().addActionListener(e -> searchBooks());
    }

    private void loadAllBooks() {
        availabilityView.getTableModel().setRowCount(0);
        for (Book b : model.getAllBooks()) {
            availabilityView.getTableModel().addRow(new Object[]{
                b.getIdBook(), b.getTitle(), b.getAuthor(),
                b.getYearPublication(), b.getState()
            });
        }
    }

    private void searchBooks() {
        String query = availabilityView.getTxtSearch().getText().trim().toLowerCase();
        availabilityView.getTableModel().setRowCount(0);

        for (Book b : model.getAllBooks()) {
            if (b.getTitle().toLowerCase().contains(query)
                    || b.getAuthor().toLowerCase().contains(query)) {
                availabilityView.getTableModel().addRow(new Object[]{
                    b.getIdBook(), b.getTitle(), b.getAuthor(),
                    b.getYearPublication(), b.getState()
                });
            }
        }
    }

    // ── Loan ──────────────────────────────────────────────────
    private void initLoanListeners() {
        loanView.getBtnRequestLoan().addActionListener(e -> requestLoan());
        loanView.getBtnClear().addActionListener(e -> clearLoanFields());

        loanView.getTableAvailable().getSelectionModel()
                .addListSelectionListener(e -> {
                    if (e.getValueIsAdjusting()) {
                        return;
                    }
                    int row = loanView.getTableAvailable().getSelectedRow();
                    if (row == -1) {
                        return;
                    }
                    loanView.getTxtBookId().setText(
                            loanView.getTableModel().getValueAt(row, 0).toString());
                    loanView.getTxtBookTitle().setText(
                            loanView.getTableModel().getValueAt(row, 1).toString());
                });
    }

    private void loadAvailableBooks() {
        loanView.getTableModel().setRowCount(0);
        for (Book b : model.getAvailableBooks()) {
            loanView.getTableModel().addRow(new Object[]{
                b.getIdBook(), b.getTitle(), b.getAuthor(), b.getYearPublication()
            });
        }
    }

    private void requestLoan() {
        // ← ahora lee directamente del input, no de un label fijo
        String email = loanView.getTxtUserEmail().getText().trim();
        String bookIdStr = loanView.getTxtBookId().getText().trim();

        if (email.isEmpty()) {
            warn(loanView, "Please enter your email.");
            return;
        }

        if (bookIdStr.isEmpty()) {
            warn(loanView, "Please select a book from the table.");
            return;
        }

        User user = model.getUserByEmail(email);
        if (user == null) {
            error(loanView, "No user found with email: " + email);
            return;
        }

        int idBook = Integer.parseInt(bookIdStr);
        Book book = model.getBookById(idBook);

        if (book == null || !"available".equalsIgnoreCase(book.getState())) {
            error(loanView, "This book is no longer available.");
            loadAvailableBooks();
            return;
        }

        model.requestLoan(user.getIdUser(), idBook);

        // Actualizar el email activo del controller para sincronizar con Returns
        this.userEmail = email;

        success(loanView, "Loan requested successfully!\nPlease return it within 14 days.");
        clearLoanFields();
        loadAvailableBooks();
        loadMyLoans();
    }

    // ── Return ────────────────────────────────────────────────
    private void initReturnListeners() {
        returnView.getBtnReturn().addActionListener(e -> returnBook());
        returnView.getBtnClear().addActionListener(e -> clearReturnFields());

        returnView.getTableMyLoans().getSelectionModel()
                .addListSelectionListener(e -> {
                    if (e.getValueIsAdjusting()) {
                        return;
                    }
                    int row = returnView.getTableMyLoans().getSelectedRow();
                    if (row == -1) {
                        return;
                    }
                    returnView.getTxtLoanId().setText(
                            returnView.getTableModel().getValueAt(row, 0).toString());
                    returnView.getTxtBookTitle().setText(
                            returnView.getTableModel().getValueAt(row, 1).toString());
                    returnView.getLblDueInfo().setText(
                            "Loan date: " + returnView.getTableModel().getValueAt(row, 2).toString());
                });
    }

    private void loadMyLoans() {
        returnView.getTableModel().setRowCount(0);
        if (userEmail == null || userEmail.isEmpty()) {
            return;
        }

        List<Loan> loans = model.getMyActiveLoans(userEmail);
        for (Loan l : loans) {
            String daysInfo = l.getDaysActive() > 14
                    ? l.getDaysActive() + " days ⚠ OVERDUE"
                    : l.getDaysActive() + " days";
            returnView.getTableModel().addRow(new Object[]{
                l.getIdLoan(), l.getBookTitle(), l.getLoanDate(), daysInfo
            });
        }
    }

    private void returnBook() {
        String loanIdStr = returnView.getTxtLoanId().getText().trim();

        if (loanIdStr.isEmpty()) {
            warn(returnView, "Please select a loan from the table.");
            return;
        }

        model.returnBook(Integer.parseInt(loanIdStr));

        success(returnView, "Book returned successfully!");
        clearReturnFields();
        loadMyLoans();
        loadAvailableBooks();
    }

    private void clearLoanFields() {
        loanView.getTxtBookId().setText("");
        loanView.getTxtBookTitle().setText("");
        loanView.getTableAvailable().clearSelection();
    }

    private void clearReturnFields() {
        returnView.getTxtLoanId().setText("");
        returnView.getTxtBookTitle().setText("");
        returnView.getLblDueInfo().setText("—");
        returnView.getTableMyLoans().clearSelection();
    }

    private void warn(java.awt.Component p, String msg) {
        JOptionPane.showMessageDialog(p, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private void error(java.awt.Component p, String msg) {
        JOptionPane.showMessageDialog(p, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void success(java.awt.Component p, String msg) {
        JOptionPane.showMessageDialog(p, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
