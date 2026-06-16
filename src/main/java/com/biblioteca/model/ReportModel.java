package com.biblioteca.model;

import java.util.List;

import com.biblioteca.entity.Report;
import com.biblioteca.repository.ReportRepository;

public class ReportModel {

    private final ReportRepository reportRepository;

    public ReportModel(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report getLiveMetrics() {
        return reportRepository.calculateCurrentMetrics();
    }

    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    public List<Report> getReportHistory() {
        return reportRepository.findAll();
    }
}
