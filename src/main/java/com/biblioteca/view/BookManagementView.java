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

public class BookManagementView extends JPanel {

    private JTextField txtId;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtYear;
    private JLabel lblState;
    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JTable tableBooks;
    private DefaultTableModel tableModel;

    public BookManagementView() {
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

        // ── Campos ────────────────────────────────────────────
        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 10, 14));
        fieldsPanel.setBackground(Color.WHITE);
        fieldsPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));

        fieldsPanel.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setBackground(new Color(240, 240, 240));
        fieldsPanel.add(txtId);

        fieldsPanel.add(new JLabel("Title:"));
        txtTitle = new JTextField();
        fieldsPanel.add(txtTitle);

        fieldsPanel.add(new JLabel("Author:"));
        txtAuthor = new JTextField();
        fieldsPanel.add(txtAuthor);

        fieldsPanel.add(new JLabel("Year:"));
        txtYear = new JTextField();
        fieldsPanel.add(txtYear);

        fieldsPanel.add(new JLabel("State:"));
        lblState = new JLabel("available");
        lblState.setForeground(new Color(39, 174, 96));
        lblState.setFont(new Font("Arial", Font.BOLD, 13));
        fieldsPanel.add(lblState);

        // ── Botones en cuadrícula 2x2 ─────────────────────────
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        // Save — azul
        btnSave.setBackground(new Color(52, 152, 219));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.BOLD, 13));
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setOpaque(true);

        // Update — naranja
        btnUpdate.setBackground(new Color(230, 126, 34));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 13));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setOpaque(true);

        // Delete — rojo
        btnDelete.setBackground(new Color(192, 57, 43));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 13));
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setOpaque(true);

        // Clear — gris
        btnClear.setBackground(new Color(149, 165, 166));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Arial", Font.BOLD, 13));
        btnClear.setFocusPainted(false);
        btnClear.setBorderPainted(false);
        btnClear.setOpaque(true);

        btnPanel.add(btnSave);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        leftWrapper.add(fieldsPanel, BorderLayout.CENTER);
        leftWrapper.add(btnPanel, BorderLayout.SOUTH);

        this.add(leftWrapper, BorderLayout.WEST);
    }

    private void initTablePanel() {
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Title", "Author", "Year", "State"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tableBooks = new JTable(tableModel);
        tableBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableBooks.setRowHeight(28);
        tableBooks.setFont(new Font("Arial", Font.PLAIN, 13));
        tableBooks.getTableHeader().setBackground(new Color(52, 152, 219));
        tableBooks.getTableHeader().setForeground(Color.WHITE);
        tableBooks.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tableBooks);
        scroll.setBorder(BorderFactory.createTitledBorder("Book Catalog"));
        this.add(scroll, BorderLayout.CENTER);
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtTitle() {
        return txtTitle;
    }

    public JTextField getTxtAuthor() {
        return txtAuthor;
    }

    public JTextField getTxtYear() {
        return txtYear;
    }

    public JLabel getLblState() {
        return lblState;
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

    public JTable getTableBooks() {
        return tableBooks;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
