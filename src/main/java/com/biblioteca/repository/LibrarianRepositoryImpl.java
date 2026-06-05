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

    private Librarian mapResultSetToLibrarian(ResultSet rs)
            throws SQLException {

        Librarian librarian = new Librarian();

        librarian.setIdentification(
                rs.getString("identification"));

        librarian.setName(
                rs.getString("name"));

        librarian.setLastname(
                rs.getString("lastname"));

        librarian.setEmail(
                rs.getString("email"));

        return librarian;
    }

    @Override
    public void save(Librarian librarian) {
        String sql = "INSERT INTO Librarian (identification, name, lastname, phone , email, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, librarian.getIdentification());
            ps.setString(2, librarian.getName());
            ps.setString(3, librarian.getLastname());
            ps.setString(4, librarian.getPhone());
            ps.setString(5, librarian.getEmail());
            ps.setString(6, librarian.getPassword());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving librarian", e);
        }
    }

    @Override
    public void update(Librarian librarian) {
        String sql = "UPDATE Librarian SET name = ?, lastname = ?, phone = ?, email = ?, password = ? WHERE identification = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, librarian.getName());
            ps.setString(2, librarian.getLastname());
            ps.setString(3, librarian.getPhone());
            ps.setString(4, librarian.getEmail());
            ps.setString(5, librarian.getPassword());
            ps.setString(6, librarian.getIdentification());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating a librarian", e);
        }
    }

    @Override
    public void delete(String identification) {
        String sql = "DELETE FROM Librarian WHERE identification = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, identification);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting a library", e);
        }
    }

    @Override
    public Librarian findByIdentification(String identification) {
        String sql = "SELECT * FROM Librarian WHERE identification = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, identification);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToLibrarian(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error searching for librarian identification", e);
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
    public boolean existsByIdentification(String identification) {
        String sql = "SELECT COUNT(*) FROM Librarian WHERE identification = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, identification);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking if a librarian exists", e);
        }

        return false;
    }

}
