package com.biblioteca.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.entity.User;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {

        User user = new User();

        user.setIdUser(rs.getInt("idUser"));
        user.setName(rs.getString("name"));
        user.setLastname(rs.getString("lastname"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setIdentification(rs.getString("identification"));

        return user;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO `User` (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE `User` SET name = ?, lastname = ?, phone = ?, identification = ?, password = ? WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getIdentification());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    @Override
    public void delete(String email) {
        String sql = "DELETE FROM `User` WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM `User` WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching user by email", e);
        }

        return null;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM `User` WHERE idUser = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error searching user by id", e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> listUsers = new ArrayList<>();

        String sql = "SELECT * FROM `User`";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listUsers.add(mapResultSetToUser(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listing all librarians", e);
        }

        return listUsers;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM `User` WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking user email", e);
        }

        return false;
    }

    @Override
    public boolean hasActiveLoans(String email) {
        String sql = "SELECT COUNT(*) FROM Loan l "
                + "INNER JOIN `User` u ON l.idUser = u.idUser "
                + "WHERE u.email = ? AND l.state = 'active'";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking active loans", e);
        }
        return false;
    }
}
