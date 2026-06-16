package com.biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReportManagementView extends JPanel {

    private JButton btnGenerateReport;
    private JButton btnRefresh;
    private JLabel lblTotalLoans;
    private JLabel lblReturnedBooks;
    private JLabel lblOverdueBooks;
    private JLabel lblActiveLoans;
    private JTable tableHistory;
    private DefaultTableModel tableModel;

    public ReportManagementView() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(new Color(245, 246, 250));

        initDashboardPanel();
        initHistoryTablePanel();
    }

    private void initDashboardPanel() {
        JPanel northPanel = new JPanel(new BorderLayout(10, 10));
        northPanel.setOpaque(false);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        controlPanel.setOpaque(false);

        btnRefresh = new JButton("⟳ Refresh");
        btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnRefresh.setFocusPainted(false);

        btnGenerateReport = new JButton("Guardar Snapshot en BD");
        btnGenerateReport.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGenerateReport.setBackground(new Color(52, 152, 219));
        btnGenerateReport.setForeground(Color.WHITE);
        btnGenerateReport.setFocusPainted(false);

        controlPanel.add(btnRefresh);
        controlPanel.add(btnGenerateReport);
        northPanel.add(controlPanel, BorderLayout.NORTH);

        JPanel cardsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        cardsPanel.setOpaque(false);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        lblTotalLoans = createCard(cardsPanel, "Total Préstamos", "0", new Color(41, 128, 185));
        lblActiveLoans = createCard(cardsPanel, "Préstamos Activos", "0", new Color(243, 156, 18));
        lblReturnedBooks = createCard(cardsPanel, "Devueltos", "0", new Color(39, 174, 96));
        lblOverdueBooks = createCard(cardsPanel, "En Mora (+14 días)", "0", new Color(192, 57, 43));

        northPanel.add(cardsPanel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
    }

    private JLabel createCard(JPanel container, String title, String value, Color accent) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTitle.setForeground(new Color(127, 140, 141));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblValue.setForeground(accent);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        container.add(card);

        return lblValue;
    }

    private void initHistoryTablePanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setOpaque(false);

        JLabel lblSection = new JLabel("Historial de Snapshots Guardados");
        lblSection.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSection.setForeground(new Color(44, 62, 80));
        lblSection.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        centerPanel.add(lblSection, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[]{"ID", "Fecha", "Total Préstamos", "Devueltos", "En Mora"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tableHistory = new JTable(tableModel);
        tableHistory.setRowHeight(26);
        tableHistory.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableHistory.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableHistory.getTableHeader().setBackground(new Color(52, 152, 219));
        tableHistory.getTableHeader().setForeground(Color.WHITE);

        centerPanel.add(new JScrollPane(tableHistory), BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    public JButton getBtnGenerateReport() {
        return btnGenerateReport;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public JLabel getLblTotalLoans() {
        return lblTotalLoans;
    }

    public JLabel getLblActiveLoans() {
        return lblActiveLoans;
    }

    public JLabel getLblReturnedBooks() {
        return lblReturnedBooks;
    }

    public JLabel getLblOverdueBooks() {
        return lblOverdueBooks;
    }

    public JTable getTableHistory() {
        return tableHistory;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void updateDashboard(int total, int active, int returned, int overdue) {
        lblTotalLoans.setText(String.valueOf(total));
        lblActiveLoans.setText(String.valueOf(active));
        lblReturnedBooks.setText(String.valueOf(returned));
        lblOverdueBooks.setText(String.valueOf(overdue));

        lblOverdueBooks.setForeground(
                overdue > 0 ? new Color(192, 57, 43) : new Color(39, 174, 96)
        );
    }
}
