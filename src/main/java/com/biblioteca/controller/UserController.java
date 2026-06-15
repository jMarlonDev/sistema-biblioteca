package com.biblioteca.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.biblioteca.entity.User;
import com.biblioteca.model.UserModel;
import com.biblioteca.view.UserManagementView;

public class UserController {

    private final UserManagementView view;
    private final UserModel model;

    public UserController(UserManagementView view, UserModel model) {
        this.view = view;
        this.model = model;

        initEvents();
        loadTableData();
    }

    private void initEvents() {
        view.getBtnSave().addActionListener(e -> saveUser());
        view.getBtnUpdate().addActionListener(e -> updateUser());
        view.getBtnDelete().addActionListener(e -> deleteUser());
        view.getBtnClear().addActionListener(e -> clearFields());

        view.getTableUsers().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.getTableUsers().getSelectedRow();

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

        List<User> list = model.getAllUsers();

        for (User user : list) {
            view.getTableModel().addRow(new Object[]{
                user.getIdUser(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
            });
        }
    }

    private void saveUser() {
        String email = view.getTxtEmail().getText().trim();
        String name = view.getTxtName().getText().trim();
        String password = view.getTxtPassword().getText().trim();

        if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields (Name, Email, Password) are required.");
            return;
        }

        if (model.existsByEmail(email)) {
            JOptionPane.showMessageDialog(view, "A user with this email already exists.");
            return;
        }

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        user.setIdentification("");
        user.setLastname("");
        user.setPhone("");

        model.saveUser(user);

        JOptionPane.showMessageDialog(view, "User saved successfully!");
        loadTableData();
        clearFields();
    }

    private void updateUser() {
        String email = view.getTxtEmail().getText().trim();
        String name = view.getTxtName().getText().trim();
        String password = view.getTxtPassword().getText().trim();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select a user from the table to update.");
            return;
        }

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Name and Password fields cannot be empty.");
            return;
        }

        User user = model.findByEmail(email);

        if (user == null) {
            JOptionPane.showMessageDialog(view, "User not found.");
            return;
        }

        user.setName(name);
        user.setPassword(password);

        model.updateUser(user);

        JOptionPane.showMessageDialog(view, "User updated successfully!");
        loadTableData();
        clearFields();
    }

    private void deleteUser() {
        String email = view.getTxtEmail().getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select a user from the table.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this user?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            model.deleteUser(email);
            JOptionPane.showMessageDialog(view, "User deleted successfully!");
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
        view.getTableUsers().clearSelection();
    }
}
