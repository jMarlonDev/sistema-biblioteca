package com.biblioteca.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.entity.Book;

public class BookRepositoryImpl implements BookRepository {

    private final Connection connection;

    public BookRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();

        book.setIdBook(rs.getInt("idBook"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYearPublication(rs.getInt("year_publication"));
        book.setState(rs.getString("state"));
        return book;
    }

    @Override
    public void save(Book book) {
        String sql = "INSERT INTO Book (title, author, year_publication, state) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYearPublication());
            ps.setString(4, book.getState());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving book", e);
        }
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE Book SET title = ?, author = ?, year_publication = ?, state = ? WHERE idBook = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYearPublication());
            ps.setString(4, book.getState());
            ps.setInt(5, book.getIdBook());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating book", e);
        }
    }

    @Override
    public void delete(int idBook) {
        String sql = "DELETE FROM Book WHERE idBook = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idBook);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book", e);
        }
    }

    @Override
    public Book findById(int idBook) {
        String sql = "SELECT * FROM Book WHERE idBook = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idBook);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToBook(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching for a book", e);
        }

        return null;
    }

    @Override
    public List<Book> findAll() {
        List<Book> listBooks = new ArrayList<>();

        String sql = "SELECT * FROM Book";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listBooks.add(mapResultSetToBook(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listing all books", e);
        }

        return listBooks;
    }

    @Override
    public List<Book> findByState(String state) {
        List<Book> listBooks = new ArrayList<>();

        String sql = "SELECT * FROM Book WHERE state = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, state);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listBooks.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching for books by status", e);
        }

        return listBooks;
    }

    @Override
    public boolean existsById(int idBook) {
        String sql = "SELECT COUNT(*) FROM Book WHERE idBook = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idBook);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if the book exists", e);
        }

        return false;
    }

}
