package com.biblioteca.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.entity.Librarian;

public class LibrarianRepositoryImpl implements LibrarianRepository {

    private final Connection connection;

    public LibrarianRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private Librarian mapResultSetToLibrarian(ResultSet rs) throws SQLException {
        Librarian librarian = new Librarian();

        librarian.setIdLibrarian(rs.getInt("idLibrarian"));
        librarian.setName(rs.getString("name"));
        librarian.setLastname(rs.getString("lastname"));
        librarian.setPhone(rs.getString("phone"));
        librarian.setEmail(rs.getString("email"));
        librarian.setPassword(rs.getString("password"));
        librarian.setIdentification(rs.getString("identification"));

        return librarian;
    }

    @Override
    public void save(Librarian librarian) {
        String sql = "INSERT INTO Librarian (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, librarian.getName());
            ps.setString(2, librarian.getEmail());
            ps.setString(3, librarian.getPassword());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving librarian", e);
        }
    }

    @Override
    public void update(Librarian librarian) {
        String sql = "UPDATE Librarian SET name = ?, lastname = ?, phone = ?, identification = ?, password = ? WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, librarian.getName());
            ps.setString(2, librarian.getLastname());
            ps.setString(3, librarian.getPhone());
            ps.setString(4, librarian.getIdentification());
            ps.setString(5, librarian.getPassword());
            ps.setString(6, librarian.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating librarian", e);
        }
    }

    @Override
    public void delete(String email) {
        String sql = "DELETE FROM Librarian WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting librarian", e);
        }
    }

    @Override
    public Librarian findByEmail(String email) {
        String sql = "SELECT * FROM Librarian WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToLibrarian(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error searching librarian by email", e);
        }
        return null;
    }

    @Override
    public Librarian findById(int id) {
        String sql = "SELECT * FROM Librarian WHERE idLibrarian = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToLibrarian(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching librarian by id", e);
        }
        return null;
    }

    @Override
    public List<Librarian> findAll() {
        List<Librarian> listLibrarians = new ArrayList<>();

        String sql = "SELECT * FROM Librarian";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listLibrarians.add(mapResultSetToLibrarian(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listing all librarians", e);
        }

        return listLibrarians;
    }

    @Override
    public boolean existsByEmail(String email) {

        String sql = "SELECT COUNT(*) FROM Librarian WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking if the librarian's email exists", e);
        }

        return false;
    }

}
