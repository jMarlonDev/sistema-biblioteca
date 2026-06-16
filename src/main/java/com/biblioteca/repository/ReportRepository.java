package com.biblioteca.repository;

import java.util.List;

import com.biblioteca.entity.Report;

public interface ReportRepository {

    Report calculateCurrentMetrics();

    void save(Report report);

    List<Report> findAll();
}
