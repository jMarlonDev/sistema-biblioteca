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

public class ReturnManagementView extends JPanel {

    private JTextField txtLoanId;
    private JTextField txtBookId;
    private JTextField txtUserEmail;
    private JButton btnReturn;
    private JButton btnClear;
    private JTable tableActiveLoans;
    private DefaultTableModel tableModel;

    public ReturnManagementView() {
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
        formPanel.setBorder(BorderFactory.createTitledBorder("Process Return"));

        formPanel.add(new JLabel("Loan ID:"));
        txtLoanId = new JTextField();
        txtLoanId.setEditable(false);
        formPanel.add(txtLoanId);

        formPanel.add(new JLabel("Book ID:"));
        txtBookId = new JTextField();
        txtBookId.setEditable(false);
        formPanel.add(txtBookId);

        formPanel.add(new JLabel("Borrower Email:"));
        txtUserEmail = new JTextField();
        txtUserEmail.setEditable(false);
        formPanel.add(txtUserEmail);

        btnReturn = new JButton("Process Return");
        btnClear = new JButton("Clear");
        btnReturn.setBackground(new Color(34, 139, 34));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setFocusPainted(false);

        formPanel.add(btnReturn);
        formPanel.add(btnClear);

        leftWrapper.add(formPanel, BorderLayout.NORTH);

        this.add(leftWrapper, BorderLayout.WEST);
    }

    private void initTablePanel() {
        tableModel = new DefaultTableModel(
                new String[]{"Loan ID", "Book ID", "Title", "User Email", "Loan Date"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tableActiveLoans = new JTable(tableModel);
        tableActiveLoans.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableActiveLoans.setRowHeight(25);
        tableActiveLoans.getTableHeader().setBackground(new Color(34, 139, 34));
        tableActiveLoans.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tableActiveLoans);
        scroll.setBorder(BorderFactory.createTitledBorder("Active Loans"));
        this.add(scroll, BorderLayout.CENTER);
    }

    public JTextField getTxtLoanId() {
        return txtLoanId;
    }

    public JTextField getTxtBookId() {
        return txtBookId;
    }

    public JTextField getTxtUserEmail() {
        return txtUserEmail;
    }

    public JButton getBtnReturn() {
        return btnReturn;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JTable getTableActiveLoans() {
        return tableActiveLoans;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
