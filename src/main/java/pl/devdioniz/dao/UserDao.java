package pl.devdioniz.dao;

import pl.devdioniz.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public boolean addUser(User user) throws SQLException {

        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPassword());
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return (rowsUpdated == 1);
    }

    public boolean deleteUserById(Long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id.toString());
        int rowsUpdated = stmt.executeUpdate();
        stmt.close();
        return (rowsUpdated == 1);
    }
}

