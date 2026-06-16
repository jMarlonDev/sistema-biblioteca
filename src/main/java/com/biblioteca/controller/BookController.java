package com.biblioteca.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.biblioteca.entity.Book;
import com.biblioteca.model.BookModel;
import com.biblioteca.view.BookManagementView;

public class BookController {

    private final BookManagementView view;
    private final BookModel model;

    public BookController(BookManagementView view, BookModel model) {
        this.view = view;
        this.model = model;
        initListeners();
        loadTableData();
    }

    private void initListeners() {
        view.getBtnSave().addActionListener(e -> saveBook());
        view.getBtnUpdate().addActionListener(e -> updateBook());
        view.getBtnDelete().addActionListener(e -> deleteBook());
        view.getBtnClear().addActionListener(e -> clearFields());

        // Al seleccionar fila → autocompletar formulario
        view.getTableBooks().getSelectionModel()
                .addListSelectionListener(e -> {
                    if (e.getValueIsAdjusting()) {
                        return;
                    }
                    int row = view.getTableBooks().getSelectedRow();
                    if (row == -1) {
                        return;
                    }

                    view.getTxtId().setText(
                            view.getTableModel().getValueAt(row, 0).toString());
                    view.getTxtTitle().setText(
                            view.getTableModel().getValueAt(row, 1).toString());
                    view.getTxtAuthor().setText(
                            view.getTableModel().getValueAt(row, 2).toString());
                    view.getTxtYear().setText(
                            view.getTableModel().getValueAt(row, 3).toString());
                    view.getLblState().setText(
                            view.getTableModel().getValueAt(row, 4).toString());
                });
    }

    // ── Operaciones ───────────────────────────────────────────
    private void saveBook() {
        String title = view.getTxtTitle().getText().trim();
        String author = view.getTxtAuthor().getText().trim();
        String yearStr = view.getTxtYear().getText().trim();

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Title, Author and Year are required.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view,
                    "Year must be a valid number.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearPublication(year);
        book.setState("available"); // siempre available al crear

        model.saveBook(book);

        JOptionPane.showMessageDialog(view,
                "Book saved successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        loadTableData();
        clearFields();
    }

    private void updateBook() {
        String idStr = view.getTxtId().getText().trim();
        String title = view.getTxtTitle().getText().trim();
        String author = view.getTxtAuthor().getText().trim();
        String yearStr = view.getTxtYear().getText().trim();

        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Please select a book from the table to update.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Title, Author and Year cannot be empty.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view,
                    "Year must be a valid number.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idBook = Integer.parseInt(idStr);
        Book book = model.findById(idBook);

        if (book == null) {
            JOptionPane.showMessageDialog(view, "Book not found.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Solo actualiza title, author y year — state no se toca
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearPublication(year);

        model.updateBook(book);

        JOptionPane.showMessageDialog(view,
                "Book updated successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        loadTableData();
        clearFields();
    }

    private void deleteBook() {
        String idStr = view.getTxtId().getText().trim();

        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Please select a book from the table.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idBook = Integer.parseInt(idStr);
        Book book = model.findById(idBook);

        // No permitir eliminar si está prestado
        if (book != null && "loaned".equalsIgnoreCase(book.getState())) {
            JOptionPane.showMessageDialog(view,
                    "Cannot delete a book that is currently loaned.\n"
                    + "Process the return first.",
                    "Delete Not Allowed", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view,
                "Are you sure you want to delete this book?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            model.deleteBook(idBook);
            JOptionPane.showMessageDialog(view,
                    "Book deleted successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableData();
            clearFields();
        }
    }

    // ── Refresh ───────────────────────────────────────────────
    private void loadTableData() {
        view.getTableModel().setRowCount(0);
        List<Book> books = model.getAllBooks();
        for (Book b : books) {
            view.getTableModel().addRow(new Object[]{
                b.getIdBook(),
                b.getTitle(),
                b.getAuthor(),
                b.getYearPublication(),
                b.getState()
            });
        }
    }

    // ── Clear ─────────────────────────────────────────────────
    private void clearFields() {
        view.getTxtId().setText("");
        view.getTxtTitle().setText("");
        view.getTxtAuthor().setText("");
        view.getTxtYear().setText("");
        view.getLblState().setText("available");
        view.getLblState().setForeground(new java.awt.Color(39, 174, 96));
        view.getTableBooks().clearSelection();
    }
}
