package com.biblioteca.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.biblioteca.entity.Librarian;
import com.biblioteca.model.LibrarianModel;
import com.biblioteca.view.LibrarianManagementView;

public class LibrarianController {

    private final LibrarianManagementView view;
    private final LibrarianModel model;

    public LibrarianController(LibrarianManagementView view, LibrarianModel model) {
        this.view = view;
        this.model = model;

        initEvents();
        loadTableData();
    }

    private void initEvents() {
        view.getBtnSave().addActionListener(e -> saveLibrarian());
        view.getBtnUpdate().addActionListener(e -> updateLibrarian());
        view.getBtnDelete().addActionListener(e -> deleteLibrarian());
        view.getBtnClear().addActionListener(e -> clearFields());

        view.getTableLibrarians().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {

                int selectedRow = view.getTableLibrarians().getSelectedRow();
                if (selectedRow != -1) {
                    view.getTxtId().setText(view.getTableModel().getValueAt(selectedRow, 0).toString());
                    view.getTxtName().setText(view.getTableModel().getValueAt(selectedRow, 1).toString());
                    view.getTxtEmail().setText(view.getTableModel().getValueAt(selectedRow, 2).toString());
                    view.getTxtPassword().setText(view.getTableModel().getValueAt(selectedRow, 3).toString());

                    view.getTxtEmail().setEditable(false);
                }
            }
        });
    }

    private void loadTableData() {
        view.getTableModel().setRowCount(0);

        List<Librarian> list = model.getAllLibrarians();

        for (Librarian librarian : list) {
            view.getTableModel().addRow(new Object[]{
                librarian.getIdLibrarian(),
                librarian.getName(),
                librarian.getEmail(),
                librarian.getPassword()
            });
        }
    }

    private void saveLibrarian() {
        String email = view.getTxtEmail().getText().trim();
        String name = view.getTxtName().getText().trim();
        String password = view.getTxtPassword().getText().trim();

        if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields (Name, Email, Password) are required.");
            return;
        }

        if (model.existsByEmail(email)) {
            JOptionPane.showMessageDialog(view, "A librarian with this email already exists.");
            return;
        }

        Librarian librarian = new Librarian();

        librarian.setName(name);
        librarian.setEmail(email);
        librarian.setPassword(password);

        librarian.setIdentification("");
        librarian.setLastname("");
        librarian.setPhone("");

        model.saveLibrarian(librarian);

        JOptionPane.showMessageDialog(view, "Librarian saved successfully!");
        loadTableData();
        clearFields();
    }

    private void updateLibrarian() {
        String email = view.getTxtEmail().getText().trim();
        String name = view.getTxtName().getText().trim();
        String password = view.getTxtPassword().getText().trim();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select a librarian from the table to update.");
            return;
        }

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Name and Password fields cannot be empty.");
            return;
        }

        Librarian librarian = model.findByEmail(email);

        if (librarian == null) {
            JOptionPane.showMessageDialog(view, "Librarian not found.");
            return;
        }

        librarian.setName(name);
        librarian.setPassword(password);

        model.updateLibrarian(librarian);

        JOptionPane.showMessageDialog(view, "Librarian updated successfully!");
        loadTableData();
        clearFields();
    }

    private void deleteLibrarian() {
        String email = view.getTxtEmail().getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select a librarian from the table.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this librarian?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.deleteLibrarian(email);
            JOptionPane.showMessageDialog(view, "Librarian deleted successfully!");
            loadTableData();
            clearFields();
        }
    }

    private void clearFields() {
        view.getTxtId().setText("");
        view.getTxtName().setText("");
        view.getTxtEmail().setText("");
        view.getTxtPassword().setText("");
        view.getTxtEmail().setEditable(true);
        view.getTableLibrarians().clearSelection();
    }
}
