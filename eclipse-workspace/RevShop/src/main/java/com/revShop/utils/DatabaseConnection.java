package com.revShop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/revShop"; // Update with your database name
    private static final String USER = "root"; // Update with your database username
    private static final String PASSWORD = "root"; // Update with your database password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the error for driver not found
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exceptions
        }
        return connection; // Return null if connection failed
    }
}
