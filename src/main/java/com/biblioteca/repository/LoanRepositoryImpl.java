package com.biblioteca.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.entity.Loan;

public class LoanRepositoryImpl implements LoanRepository {

    private final Connection connection;

    public LoanRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void registerLoan(Loan loan) {
        String sqlInsert = "INSERT INTO Loan (idUser, idBook, loan_date, state) " + "VALUES (?, ?, CURDATE(), 'active')";

        try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
            ps.setInt(1, loan.getIdUser());
            ps.setInt(2, loan.getIdBook());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error registering loan", e);
        }

        String sqlBook = "UPDATE Book SET state = 'loaned' WHERE idBook = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlBook)) {
            ps.setInt(1, loan.getIdBook());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating book state", e);
        }
    }

    @Override
    public void registerReturn(int idLoan) {
        int idBook = -1;
        String sqlFind = "SELECT idBook FROM Loan WHERE idLoan = ? AND state = 'active'";

        try (PreparedStatement ps = connection.prepareStatement(sqlFind)) {
            ps.setInt(1, idLoan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idBook = rs.getInt("idBook");
            } else {
                throw new RuntimeException("Loan not found or already returned");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding loan", e);
        }

        String sqlLoan = "UPDATE Loan SET return_date = CURDATE(), state = 'returned' " + "WHERE idLoan = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlLoan)) {
            ps.setInt(1, idLoan);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing loan", e);
        }

        String sqlBook = "UPDATE Book SET state = 'available' WHERE idBook = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlBook)) {
            ps.setInt(1, idBook);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error restoring book state", e);
        }
    }

    @Override
    public List<Loan> findActiveLoans() {
        List<Loan> list = new ArrayList<>();

        String sql = "SELECT l.idLoan, l.idUser, l.idBook, l.loan_date, l.state, "
                + "       u.email, b.title "
                + "FROM Loan l "
                + "INNER JOIN `User` u ON l.idUser = u.idUser "
                + "INNER JOIN Book  b ON l.idBook  = b.idBook "
                + "WHERE l.state = 'active' "
                + "ORDER BY l.loan_date DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Loan loan = new Loan();
                loan.setIdLoan(rs.getInt("idLoan"));
                loan.setIdUser(rs.getInt("idUser"));
                loan.setIdBook(rs.getInt("idBook"));
                loan.setLoanDate(rs.getString("loan_date"));
                loan.setState(rs.getString("state"));
                loan.setUserEmail(rs.getString("email"));
                loan.setBookTitle(rs.getString("title"));

                list.add(loan);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding active loans", e);
        }

        return list;
    }

    @Override
    public List<Loan> findActiveLoansByUserEmail(String email) {
        List<Loan> list = new ArrayList<>();

        String sql
                = "SELECT l.idLoan, l.idUser, l.idBook, l.loan_date, l.state, "
                + "       u.email, b.title, "
                + "       DATEDIFF(CURRENT_DATE, l.loan_date) AS days_active "
                + "FROM Loan l "
                + "INNER JOIN `User` u ON l.idUser = u.idUser "
                + "INNER JOIN Book   b ON l.idBook  = b.idBook "
                + "WHERE l.state = 'active' AND u.email = ? "
                + "ORDER BY l.loan_date DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Loan loan = new Loan();
                loan.setIdLoan(rs.getInt("idLoan"));
                loan.setIdUser(rs.getInt("idUser"));
                loan.setIdBook(rs.getInt("idBook"));
                loan.setLoanDate(rs.getString("loan_date"));
                loan.setState(rs.getString("state"));
                loan.setUserEmail(rs.getString("email"));
                loan.setBookTitle(rs.getString("title"));
                loan.setDaysActive(rs.getInt("days_active")); // ← nuevo campo
                list.add(loan);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding loans by user email", e);
        }

        return list;
    }
}
