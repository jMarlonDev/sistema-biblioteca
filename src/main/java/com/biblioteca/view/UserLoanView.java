package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class UserLoanView extends JPanel {

    private JTextField txtUserEmail;   // ← ahora es campo de la clase, con getter
    private JTextField txtBookId;
    private JTextField txtBookTitle;
    private JButton btnRequestLoan;
    private JButton btnClear;
    private JTable tableAvailable;
    private DefaultTableModel tableModel;

    public UserLoanView() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.WHITE);
        initFormPanel();
        initTablePanel();
    }

    private void initFormPanel() {
        JPanel leftWrapper = new JPanel(new BorderLayout(0, 15));
        leftWrapper.setBackground(Color.WHITE);
        leftWrapper.setPreferredSize(new Dimension(280, 0));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 14));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder("Request Loan"));

        // Email — campo editable real
        formPanel.add(new JLabel("Your Email:"));
        txtUserEmail = new JTextField();
        formPanel.add(txtUserEmail);

        // Book ID — solo lectura, viene de la tabla
        formPanel.add(new JLabel("Book ID:"));
        txtBookId = new JTextField();
        txtBookId.setEditable(false);
        txtBookId.setBackground(new Color(240, 240, 240));
        formPanel.add(txtBookId);

        // Book Title — solo lectura, viene de la tabla
        formPanel.add(new JLabel("Book Title:"));
        txtBookTitle = new JTextField();
        txtBookTitle.setEditable(false);
        txtBookTitle.setBackground(new Color(240, 240, 240));
        formPanel.add(txtBookTitle);

        // Botones
        btnRequestLoan = new JButton("Request Loan");
        btnClear = new JButton("Clear");

        btnRequestLoan.setBackground(new Color(39, 174, 96));
        btnRequestLoan.setForeground(Color.WHITE);
        btnRequestLoan.setFocusPainted(false);
        btnRequestLoan.setBorderPainted(false);
        btnRequestLoan.setOpaque(true);
        btnRequestLoan.setFont(new Font("Arial", Font.BOLD, 13));

        btnClear.setFocusPainted(false);
        btnClear.setFont(new Font("Arial", Font.BOLD, 13));

        formPanel.add(btnRequestLoan);
        formPanel.add(btnClear);

        JLabel hint = new JLabel("<html><i>Click a row to select a book</i></html>");
        hint.setForeground(Color.GRAY);
        hint.setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 0));

        leftWrapper.add(formPanel, BorderLayout.NORTH);
        leftWrapper.add(hint, BorderLayout.CENTER);
        this.add(leftWrapper, BorderLayout.WEST);
    }

    private void initTablePanel() {
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Title", "Author", "Year"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tableAvailable = new JTable(tableModel);
        tableAvailable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAvailable.setRowHeight(25);
        tableAvailable.setFont(new Font("Arial", Font.PLAIN, 13));
        tableAvailable.getTableHeader().setBackground(new Color(39, 174, 96));
        tableAvailable.getTableHeader().setForeground(Color.WHITE);
        tableAvailable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tableAvailable);
        scroll.setBorder(BorderFactory.createTitledBorder("Available Books"));
        this.add(scroll, BorderLayout.CENTER);
    }

    // ── Getters ───────────────────────────────────────────────
    public JTextField getTxtUserEmail() {
        return txtUserEmail;
    }

    public JTextField getTxtBookId() {
        return txtBookId;
    }

    public JTextField getTxtBookTitle() {
        return txtBookTitle;
    }

    public JButton getBtnRequestLoan() {
        return btnRequestLoan;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JTable getTableAvailable() {
        return tableAvailable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    // Permite al controller pre-rellenar el email cuando el usuario hace login
    public void setUserEmail(String email) {
        txtUserEmail.setText(email);
    }
}
