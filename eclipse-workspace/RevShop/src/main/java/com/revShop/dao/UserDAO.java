package com.revShop.dao;

import com.revShop.models.User;
import com.revShop.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Check if the email already exists
    public boolean emailExists(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE LOWER(email) = LOWER(?)"; // Use LOWER to ensure case insensitivity
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // returns true if there is a record
        }
    }
    
        public User getUserById(int userId) {
            User user = null;
            String sql = "SELECT id,email,role FROM users WHERE id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                   
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                   
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        }
    


    // Save user with plain text password
    public void saveUser(User user) throws SQLException {
        String query = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword()); // Store plain text password
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        }
    }

    // Find user by email
    public User findUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE LOWER(email) = LOWER(?)"; // Use LOWER to ensure case insensitivity
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id")); // Ensure you are retrieving the user ID
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password")); // Retrieve plain text password
                user.setRole(resultSet.getString("role"));
                return user;
            }
        }
        return null; // Return null if user is not found
    }
}
