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

public class UserReturnView extends JPanel {

    private JTextField txtLoanId;
    private JTextField txtBookTitle;
    private JLabel lblDueInfo;
    private JButton btnReturn;
    private JButton btnClear;
    private JTable tableMyLoans;
    private DefaultTableModel tableModel;

    public UserReturnView() {
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
        formPanel.setBorder(BorderFactory.createTitledBorder("Return Book"));

        formPanel.add(new JLabel("Loan ID:"));
        txtLoanId = new JTextField();
        txtLoanId.setEditable(false);
        txtLoanId.setBackground(new Color(240, 240, 240));
        formPanel.add(txtLoanId);

        formPanel.add(new JLabel("Book Title:"));
        txtBookTitle = new JTextField();
        txtBookTitle.setEditable(false);
        txtBookTitle.setBackground(new Color(240, 240, 240));
        formPanel.add(txtBookTitle);

        formPanel.add(new JLabel("Loan Date:"));
        lblDueInfo = new JLabel("—");
        lblDueInfo.setFont(new Font("Arial", Font.PLAIN, 13));
        formPanel.add(lblDueInfo);

        btnReturn = new JButton("Return Book");
        btnClear = new JButton("Clear");

        btnReturn.setBackground(new Color(192, 57, 43));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setFocusPainted(false);
        btnReturn.setBorderPainted(false);
        btnReturn.setOpaque(true);
        btnReturn.setFont(new Font("Arial", Font.BOLD, 13));

        btnClear.setFocusPainted(false);
        btnClear.setFont(new Font("Arial", Font.BOLD, 13));

        formPanel.add(btnReturn);
        formPanel.add(btnClear);

        JLabel hint = new JLabel("<html><i>Click a row to select your loan</i></html>");
        hint.setForeground(Color.GRAY);
        hint.setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 0));

        leftWrapper.add(formPanel, BorderLayout.NORTH);
        leftWrapper.add(hint, BorderLayout.CENTER);
        this.add(leftWrapper, BorderLayout.WEST);
    }

    private void initTablePanel() {
        tableModel = new DefaultTableModel(
                new String[]{"Loan ID", "Book Title", "Loan Date", "Days Active"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tableMyLoans = new JTable(tableModel);
        tableMyLoans.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableMyLoans.setRowHeight(26);
        tableMyLoans.setFont(new Font("Arial", Font.PLAIN, 13));
        tableMyLoans.getTableHeader().setBackground(new Color(192, 57, 43));
        tableMyLoans.getTableHeader().setForeground(Color.WHITE);
        tableMyLoans.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tableMyLoans);
        scroll.setBorder(BorderFactory.createTitledBorder("My Active Loans"));
        this.add(scroll, BorderLayout.CENTER);
    }

    public JTextField getTxtLoanId() {
        return txtLoanId;
    }

    public JTextField getTxtBookTitle() {
        return txtBookTitle;
    }

    public JLabel getLblDueInfo() {
        return lblDueInfo;
    }

    public JButton getBtnReturn() {
        return btnReturn;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JTable getTableMyLoans() {
        return tableMyLoans;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
