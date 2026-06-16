package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class LoanManagementView extends JPanel {

    private JTextField txtUserEmail;
    private JTextField txtBookId;
    private JTextField txtBookTitle;
    private JButton btnLoan;
    private JButton btnClear;
    private JTable tableAvailableBooks;
    private DefaultTableModel tableModel;

    public LoanManagementView() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.WHITE);
        initFormPanel();
        initTablePanel();
    }

    private void initFormPanel() {
        JPanel leftWrapper = new JPanel(new BorderLayout());
        leftWrapper.setBackground(Color.WHITE);
        leftWrapper.setPreferredSize(new Dimension(320, 0));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 12));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder("Issue Loan"));

        formPanel.add(new JLabel("User Email:"));
        txtUserEmail = new JTextField();
        formPanel.add(txtUserEmail);

        formPanel.add(new JLabel("Book ID:"));
        txtBookId = new JTextField();
        txtBookId.setEditable(false);
        formPanel.add(txtBookId);

        formPanel.add(new JLabel("Book Title:"));
        txtBookTitle = new JTextField();
        txtBookTitle.setEditable(false);
        formPanel.add(txtBookTitle);

        btnLoan = new JButton("Issue Loan");
        btnClear = new JButton("Clear");
        btnLoan.setBackground(new Color(70, 130, 180));
        btnLoan.setForeground(Color.WHITE);
        btnLoan.setFocusPainted(false);

        formPanel.add(btnLoan);
        formPanel.add(btnClear);

        leftWrapper.add(formPanel, BorderLayout.NORTH);

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

        tableAvailableBooks = new JTable(tableModel);
        tableAvailableBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAvailableBooks.setRowHeight(25);
        tableAvailableBooks.getTableHeader().setBackground(new Color(70, 130, 180));
        tableAvailableBooks.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tableAvailableBooks);
        scroll.setBorder(BorderFactory.createTitledBorder("Available Books"));
        this.add(scroll, BorderLayout.CENTER);
    }

    public JTextField getTxtUserEmail() {
        return txtUserEmail;
    }

    public JTextField getTxtBookId() {
        return txtBookId;
    }

    public JTextField getTxtBookTitle() {
        return txtBookTitle;
    }

    public JButton getBtnLoan() {
        return btnLoan;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JTable getTableAvailableBooks() {
        return tableAvailableBooks;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
