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

    private Loan mapResultSetToLoan(ResultSet rs) throws SQLException {

        Loan loan = new Loan();
        loan.setIdLoan(rs.getInt("idLoan"));
        loan.setIdUser(rs.getInt("idUser"));
        loan.setIdBook(rs.getInt("idBook"));
        loan.setLoanDate(rs.getString("loan_date"));
        loan.setReturnDate(rs.getString("return_date"));
        loan.setState(rs.getString("state"));

        return loan;
    }

    @Override
    public void save(Loan loan) {
        String sql = "INSERT INTO Loan (idUser, idBook, loan_date, return_date, state) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, loan.getIdUser());
            ps.setInt(2, loan.getIdBook());
            ps.setString(3, loan.getLoanDate());
            ps.setString(4, loan.getReturnDate());
            ps.setString(5, loan.getState());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving loan", e);
        }
    }

    @Override
    public void update(Loan loan) {
        String sql = "UPDATE Loan SET idUser = ?, idBook = ?, loan_date = ?, return_date = ?, state = ? WHERE idLoan = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, loan.getIdUser());
            ps.setInt(2, loan.getIdBook());
            ps.setString(3, loan.getLoanDate());
            ps.setString(4, loan.getReturnDate());
            ps.setString(5, loan.getState());
            ps.setInt(6, loan.getIdLoan());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating loan", e);
        }
    }

    @Override
    public void delete(int idLoan) {
        String sql = "DELETE FROM Loan WHERE idLoan = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idLoan);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting loan", e);
        }
    }

    @Override
    public Loan findById(int idLoan) {
        String sql = "SELECT * FROM Loan WHERE idLoan = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idLoan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToLoan(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error searching for loan", e);
        }

        return null;
    }

    @Override
    public List<Loan> findAll() {
        List<Loan> listLoans = new ArrayList<>();

        String sql = "SELECT * FROM Loan";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listLoans.add(mapResultSetToLoan(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listing all loans", e);
        }

        return listLoans;
    }

    @Override
    public List<Loan> findByState(String state) {
        List<Loan> listLoans = new ArrayList<>();
        String sql = "SELECT * FROM Loan WHERE state = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, state);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listLoans.add(mapResultSetToLoan(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching for loans by state", e);
        }

        return listLoans;
    }

    @Override
    public List<Loan> findByUserId(int idUser) {
        List<Loan> listLoans = new ArrayList<>();
        String sql = "SELECT * FROM Loan WHERE idUser = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listLoans.add(mapResultSetToLoan(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching for user loans", e);
        }

        return listLoans;
    }

    @Override
    public List<Loan> findByBookId(int idBook) {
        List<Loan> listLoans = new ArrayList<>();
        String sql = "SELECT * FROM Loan WHERE idBook = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idBook);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listLoans.add(mapResultSetToLoan(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching book history", e);
        }

        return listLoans;
    }

    @Override
    public List<Loan> findActiveLoans() {
        return findByState("active");
    }

    @Override
    public boolean hasActiveLoansByUser(int idUser) {
        String sql = "SELECT COUNT(*) FROM Loan WHERE idUser = ? AND state = 'active'";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error verifying active loans", e);
        }

        return false;
    }
}
