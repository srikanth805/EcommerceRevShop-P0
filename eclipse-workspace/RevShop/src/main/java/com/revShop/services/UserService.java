package com.revShop.services;

import com.revShop.dao.UserDAO;
import com.revShop.models.User;

import java.sql.SQLException;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }
    public User getUserById(int userId) {
        User user = null;
        try {
            UserDAO userDAO = new UserDAO();
            user = userDAO.getUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    // Register a new user with role
    public boolean registerUser(String email, String password, String role) throws SQLException {
        if (userDAO.emailExists(email)) {
            System.out.println("Email already exists.");
            return false;
        }

        // Ensure role is valid
        if (!isValidRole(role)) {
            System.out.println("Invalid role. Role must be 'buyer' or 'seller'.");
            return false;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // Store password as plain text
        user.setRole(role); // Set user role
        userDAO.saveUser(user);
        System.out.println("User registered successfully with role: " + role);
        return true;
    }

    // Login user
    public User loginUser(String email, String password) throws SQLException {
        User user = userDAO.findUserByEmail(email);
        if (user != null && password.equals(user.getPassword())) { // Check plain text password
            System.out.println("User logged in successfully as: " + user.getRole());
            return user; // Return user object on successful login
        }
        System.out.println("User login failed for email: " + email);
        return null; // Login failed
    }

    // Validate role input
    private boolean isValidRole(String role) {
        return "buyer".equals(role) || "seller".equals(role);
    }
}
