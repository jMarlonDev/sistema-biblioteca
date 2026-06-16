package com.biblioteca.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.biblioteca.entity.Report;
import com.biblioteca.model.ReportModel;
import com.biblioteca.view.ReportManagementView;

public class ReportController {

    private final ReportManagementView view;
    private final ReportModel model;

    public ReportController(ReportManagementView view, ReportModel model) {
        this.view = view;
        this.model = model;
        initListeners();

        refresh();
    }

    private void initListeners() {

        view.getBtnRefresh().addActionListener(e -> refresh());

        view.getBtnGenerateReport().addActionListener(e -> saveSnapshot());
    }

    public void refresh() {
        updateDashboardCards();
        loadHistoryTable();
    }

    private void updateDashboardCards() {
        Report live = model.getLiveMetrics();

        int total = live.getTotalLoans();
        int returned = live.getReturnedBooks();
        int overdue = live.getOverdueBooks();
        int active = total - returned;

        view.updateDashboard(total, active, returned, overdue);
    }

    private void loadHistoryTable() {
        view.getTableModel().setRowCount(0);
        List<Report> history = model.getReportHistory();
        for (Report r : history) {
            view.getTableModel().addRow(new Object[]{
                r.getIdReport(),
                r.getReportDate(),
                r.getTotalLoans(),
                r.getReturnedBooks(),
                r.getOverdueBooks()
            });
        }
    }

    private void saveSnapshot() {
        Report snapshot = model.getLiveMetrics();
        model.saveReport(snapshot);
        refresh();

        JOptionPane.showMessageDialog(
                view,
                "Snapshot guardado correctamente en la base de datos.",
                "Reporte Guardado",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
