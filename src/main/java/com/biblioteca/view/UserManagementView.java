package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UserManagementView extends JPanel {

    private JTextField txtId, txtName, txtEmail, txtPassword;
    private JButton btnSave, btnUpdate, btnDelete, btnClear;
    private JTable tableUsers;
    private DefaultTableModel tableModel;

    public UserManagementView() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.WHITE);

        initFormPanel();
        initTablePanel();
    }

    private void initFormPanel() {
        JPanel leftWrapper = new JPanel(new BorderLayout());
        leftWrapper.setBackground(Color.WHITE);
        leftWrapper.setPreferredSize(new Dimension(350, 0));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 12));
        formPanel.setBackground(Color.WHITE);

        formPanel.add(new JLabel("ID (Selección):"));
        txtId = new JTextField();
        txtId.setEditable(false);
        formPanel.add(txtId);

        formPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);

        JPanel actionsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        actionsPanel.setBackground(Color.WHITE);

        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        actionsPanel.add(btnSave);
        actionsPanel.add(btnUpdate);
        actionsPanel.add(btnDelete);
        actionsPanel.add(btnClear);

        formPanel.add(new JLabel("Actions:"));
        formPanel.add(actionsPanel);

        leftWrapper.add(formPanel, BorderLayout.NORTH);
        this.add(leftWrapper, BorderLayout.WEST);
    }

    private void initTablePanel() {
        String[] columns = {"ID", "Name", "Email", "Password"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableUsers = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableUsers);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JTable getTableUsers() {
        return tableUsers;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
