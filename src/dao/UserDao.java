package dao;

import model.User;
import java.sql.*;

public class UserDao {
    public boolean registerUser(User user) throws SQLException {
        validateUser(user);
        
        if (userExists(user.getUsername(), user.getEmail())) {
            return false;
        }

        String query = "INSERT INTO users (national_id, username, email, password, phone_number) " +
                       "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setString(1, user.getNationalId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword()); // Note: In production, hash this password
            ps.setString(5, user.getPhoneNumber());

            return ps.executeUpdate() > 0;
        }
    }

    private void validateUser(User user) throws SQLException {
        if (user.getNationalId() == null || user.getNationalId().trim().isEmpty()) {
            throw new SQLException("National ID is required");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new SQLException("Username is required");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new SQLException("Invalid email format");
        }
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new SQLException("Password must be at least 8 characters");
        }
        if (user.getPhoneNumber() == null || !user.getPhoneNumber().matches("^\\+?[0-9]{10,15}$")) {
            throw new SQLException("Phone must be 10-15 digits (optional + prefix)");
        }
    }

    private boolean userExists(String username, String email) throws SQLException {
        String query = "SELECT 1 FROM users WHERE username = ? OR email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, email);
            return ps.executeQuery().next();
        }
    }

    public User getUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getString("national_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number")
                    );
                }
            }
        }
        return null;
    }
}