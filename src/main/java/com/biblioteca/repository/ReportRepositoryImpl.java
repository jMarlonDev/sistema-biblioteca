package com.biblioteca.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.entity.Report;

public class ReportRepositoryImpl implements ReportRepository {

    private final Connection connection;

    public ReportRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Report calculateCurrentMetrics() {
        Report report = new Report();

        String sqlTotal = "SELECT COUNT(*) FROM Loan";
        String sqlReturned = "SELECT COUNT(*) FROM Loan WHERE state = 'returned'";

        String sqlOverdue = "SELECT COUNT(*) FROM Loan "
                + "WHERE state = 'active' "
                + "AND DATEDIFF(CURRENT_DATE, loan_date) > 14";

        try {
            try (PreparedStatement ps = connection.prepareStatement(sqlTotal); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    report.setTotalLoans(rs.getInt(1));
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(sqlReturned); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    report.setReturnedBooks(rs.getInt(1));
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(sqlOverdue); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    report.setOverdueBooks(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error calculating loan metrics", e);
        }

        return report;
    }

    @Override
    public void save(Report report) {
        String sql = "INSERT INTO Report (report_date, total_loans, returned_books, overdue_books) "
                + "VALUES (CURRENT_DATE, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, report.getTotalLoans());
            ps.setInt(2, report.getReturnedBooks());
            ps.setInt(3, report.getOverdueBooks());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    report.setIdReport(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving report", e);
        }
    }

    @Override
    public List<Report> findAll() {
        List<Report> list = new ArrayList<>();
        String sql = "SELECT idReport, report_date, total_loans, returned_books, overdue_books "
                + "FROM Report ORDER BY report_date DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Report r = new Report();
                r.setIdReport(rs.getInt("idReport"));
                r.setReportDate(rs.getString("report_date"));
                r.setTotalLoans(rs.getInt("total_loans"));
                r.setReturnedBooks(rs.getInt("returned_books"));
                r.setOverdueBooks(rs.getInt("overdue_books"));
                list.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listing reports", e);
        }

        return list;
    }
}
