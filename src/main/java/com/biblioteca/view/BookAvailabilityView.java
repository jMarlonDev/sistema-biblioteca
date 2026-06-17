package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BookAvailabilityView extends JPanel {

    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnShowAll;
    private JTable tableBooks;
    private DefaultTableModel tableModel;

    public BookAvailabilityView() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.WHITE);
        initSearchPanel();
        initTablePanel();
    }

    private void initSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Available Books"));

        searchPanel.add(new JLabel("Title or Author:"));

        txtSearch = new JTextField(25);
        searchPanel.add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBackground(new Color(52, 152, 219));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        btnSearch.setOpaque(true);
        searchPanel.add(btnSearch);

        btnShowAll = new JButton("Show All Available");
        btnShowAll.setFocusPainted(false);
        searchPanel.add(btnShowAll);

        this.add(searchPanel, BorderLayout.NORTH);
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
        tableBooks.setRowHeight(26);
        tableBooks.setFont(new Font("Arial", Font.PLAIN, 13));
        tableBooks.getTableHeader().setBackground(new Color(52, 152, 219));
        tableBooks.getTableHeader().setForeground(Color.WHITE);
        tableBooks.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tableBooks);
        scroll.setBorder(BorderFactory.createTitledBorder("Available Books Catalog"));
        this.add(scroll, BorderLayout.CENTER);
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnShowAll() {
        return btnShowAll;
    }

    public JTable getTableBooks() {
        return tableBooks;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
