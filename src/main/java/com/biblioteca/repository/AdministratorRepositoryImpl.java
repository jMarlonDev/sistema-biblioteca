package com.biblioteca.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biblioteca.entity.Administrator;

public class AdministratorRepositoryImpl implements AdministratorRepository {

    private final Connection connection;

    public AdministratorRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private Administrator mapResultSetToAdmin(ResultSet rs) throws SQLException {

        Administrator admin = new Administrator();

        admin.setIdAdministrator(rs.getInt("idAdministrator"));
        admin.setIdentification(rs.getString("identification"));
        admin.setName(rs.getString("name"));
        admin.setLastname(rs.getString("lastname"));
        admin.setPhone(rs.getString("phone"));
        admin.setEmail(rs.getString("email"));
        admin.setPassword(rs.getString("password"));

        return admin;
    }

    @Override
    public Administrator findByEmail(String email) {
        String sql = "SELECT * FROM Administrator WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToAdmin(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error searching for administrator by email: ", e);
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM Administrator WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking if the administrator exists: ", e);
        }
        return false;
    }

}
