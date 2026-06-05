package com.biblioteca.repository;

import java.util.List;

import com.biblioteca.entity.Report;

public interface ReportRepository {

    void save(Report report);

    void update(Report report);

    void delete(int idReport);

    Report findById(int idReport);

    List<Report> findAll();

}
