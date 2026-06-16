package com.biblioteca.controller;


import javax.swing.JOptionPane;

import com.biblioteca.entity.Book;
import com.biblioteca.entity.Loan;
import com.biblioteca.entity.User;
import com.biblioteca.model.LoanModel;
import com.biblioteca.view.LoanManagementView;
import com.biblioteca.view.ReturnManagementView;

public class LoanController {

    private final LoanManagementView   loanView;
    private final ReturnManagementView returnView;
    private final LoanModel            model;

    private ReportController reportController;

    public LoanController(LoanManagementView loanView,
                          ReturnManagementView returnView,
                          LoanModel model) {
        this.loanView   = loanView;
        this.returnView = returnView;
        this.model      = model;

        initLoanListeners();
        initReturnListeners();
        refreshAllData();
    }

    public void setReportController(ReportController reportController) {
        this.reportController = reportController;
    }


    private void initLoanListeners() {
        loanView.getBtnLoan().addActionListener(e  -> processLoan());
        loanView.getBtnClear().addActionListener(e -> clearLoanFields());

        loanView.getTableAvailableBooks().getSelectionModel()
            .addListSelectionListener(e -> {
                if (e.getValueIsAdjusting()) return;
                int row = loanView.getTableAvailableBooks().getSelectedRow();
                if (row == -1) return;
                loanView.getTxtBookId().setText(
                    loanView.getTableModel().getValueAt(row, 0).toString());
                loanView.getTxtBookTitle().setText(
                    loanView.getTableModel().getValueAt(row, 1).toString());
            });
    }

    private void initReturnListeners() {
        returnView.getBtnReturn().addActionListener(e -> processReturn());
        returnView.getBtnClear().addActionListener(e  -> clearReturnFields());

        returnView.getTableActiveLoans().getSelectionModel()
            .addListSelectionListener(e -> {
                if (e.getValueIsAdjusting()) return;
                int row = returnView.getTableActiveLoans().getSelectedRow();
                if (row == -1) return;
                returnView.getTxtLoanId().setText(
                    returnView.getTableModel().getValueAt(row, 0).toString());
                returnView.getTxtBookId().setText(
                    returnView.getTableModel().getValueAt(row, 1).toString());
                returnView.getTxtUserEmail().setText(
                    returnView.getTableModel().getValueAt(row, 3).toString());
            });
    }


    private void processLoan() {
        String email     = loanView.getTxtUserEmail().getText().trim();
        String bookIdStr = loanView.getTxtBookId().getText().trim();

        if (email.isEmpty() || bookIdStr.isEmpty()) {
            warn(loanView, "Enter user email and select a book from the table.");
            return;
        }

        User user = model.getUserByEmail(email);
        if (user == null) {
            error(loanView, "No user found with email: " + email);
            return;
        }

        int  idBook = Integer.parseInt(bookIdStr);
        Book book   = model.getBookById(idBook);

        if (book == null || !"available".equalsIgnoreCase(book.getState())) {
            error(loanView, "Book is no longer available.");
            refreshAllData();
            return;
        }

        Loan loan = new Loan();
        loan.setIdUser(user.getIdUser());
        loan.setIdBook(idBook);
        model.checkoutLoan(loan);

        success(loanView, "Loan issued successfully!");
        clearLoanFields();
        refreshAllData();
        notifyReportController(); 
    }

    private void processReturn() {
        String loanIdStr = returnView.getTxtLoanId().getText().trim();

        if (loanIdStr.isEmpty()) {
            warn(returnView, "Select an active loan from the table.");
            return;
        }

        model.processReturn(Integer.parseInt(loanIdStr));
        success(returnView, "Book returned successfully!");
        clearReturnFields();
        refreshAllData();
        notifyReportController(); 
    }


    public void refreshAllData() {
        loanView.getTableModel().setRowCount(0);
        for (Book b : model.getAvailableBooks()) {
            loanView.getTableModel().addRow(new Object[]{
                b.getIdBook(), b.getTitle(), b.getAuthor(), b.getYearPublication()
            });
        }

        returnView.getTableModel().setRowCount(0);
        for (Loan l : model.getActiveLoans()) {
            returnView.getTableModel().addRow(new Object[]{
                l.getIdLoan(),
                l.getIdBook(),
                l.getBookTitle(),
                l.getUserEmail(),
                l.getLoanDate()
            });
        }
    }

    private void notifyReportController() {
        if (reportController != null) {
            reportController.refresh();
        }
    }


    private void clearLoanFields() {
        loanView.getTxtUserEmail().setText("");
        loanView.getTxtBookId().setText("");
        loanView.getTxtBookTitle().setText("");
        loanView.getTableAvailableBooks().clearSelection();
    }

    private void clearReturnFields() {
        returnView.getTxtLoanId().setText("");
        returnView.getTxtBookId().setText("");
        returnView.getTxtUserEmail().setText("");
        returnView.getTableActiveLoans().clearSelection();
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