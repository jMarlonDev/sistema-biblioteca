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

    private User mapResultSetToUser(ResultSet rs)
            throws SQLException {

        User user = new User();

        user.setIdentification(
                rs.getString("identification"));

        user.setName(
                rs.getString("name"));

        user.setLastname(
                rs.getString("lastname"));

        user.setEmail(
                rs.getString("email"));

        return user;
    }

    @Override
    public void save(User user) {

        String sql = "INSERT INTO `User` (identification, name, lastname, phone, email, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getIdentification());
            ps.setString(2, user.getName());
            ps.setString(3, user.getLastname());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE `User` SET name = ?, lastname = ?, phone = ?, email = ?, password = ? WHERE identification = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getIdentification());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    @Override
    public void delete(String identification) {
        String sql = "DELETE FROM `User` WHERE identification = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, identification);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    @Override
    public User findByIdentification(String identification) {
        String sql = "SELECT * FROM `User` WHERE identification = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, identification);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching for user by identification", e);
        }

        return null;
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
            throw new RuntimeException("Error searching for user by email", e);
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
    public boolean existsByIdentification(String identification) {
        String sql = "SELECT COUNT(*) FROM `User` WHERE identification = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, identification);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error verifying user by identification", e);
        }

        return false;
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
            throw new RuntimeException("Error verifying user by email", e);
        }

        return false;
    }

}
