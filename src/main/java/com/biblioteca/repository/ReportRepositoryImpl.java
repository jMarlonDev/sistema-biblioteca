package com.biblioteca.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.entity.Report;

public class ReportRepositoryImpl implements ReportRepository {

    private final Connection connection;

    public ReportRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private Report mapResultSetToReport(ResultSet rs) throws SQLException {
        Report report = new Report();
        report.setIdReport(rs.getInt("idReport"));
        report.setReportDate(rs.getString("report_date"));
        report.setTotalLoans(rs.getInt("total_loans"));
        report.setReturnedBooks(rs.getInt("returned_books"));
        report.setOverdueBooks(rs.getInt("overdue_books"));

        return report;
    }

    @Override
    public void save(Report report) {
        String sql = "INSERT INTO Report (report_date, total_loans, returned_books, overdue_books) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, report.getReportDate());
            ps.setInt(2, report.getTotalLoans());
            ps.setInt(3, report.getReturnedBooks());
            ps.setInt(4, report.getOverdueBooks());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving report", e);
        }
    }

    @Override
    public void update(Report report) {
        String sql = "UPDATE Report SET report_date = ?, total_loans = ?, returned_books = ?, overdue_books = ? WHERE idReport = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, report.getReportDate());
            ps.setInt(2, report.getTotalLoans());
            ps.setInt(3, report.getReturnedBooks());
            ps.setInt(4, report.getOverdueBooks());
            ps.setInt(5, report.getIdReport());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating report", e);
        }
    }

    @Override
    public void delete(int idReport) {
        String sql = "DELETE FROM Report WHERE idReport = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idReport);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting report", e);
        }
    }

    @Override
    public Report findById(int idReport) {
        String sql = "SELECT * FROM Report WHERE idReport = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idReport);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToReport(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching for a report", e);
        }

        return null;
    }

    @Override
    public List<Report> findAll() {
        List<Report> listReports = new ArrayList<>();

        String sql = "SELECT * FROM Loan";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listReports.add(mapResultSetToReport(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listing all reports", e);
        }

        return listReports;
    }
}
