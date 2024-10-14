package com.revShop.dao;

import com.revShop.models.Cart;
import com.revShop.utils.DatabaseConnection;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO {
    public void saveCart(Cart cart, int userId) {
        String query = "INSERT INTO carts (user_id, product_id, quantity) VALUES (?, ?, ?) " +
                       "ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity)";
        try (Connection connection = DatabaseConnection.getConnection()) {
            for (Map.Entry<Integer, Integer> entry : cart.getItems().entrySet()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setInt(2, entry.getKey());
                    preparedStatement.setInt(3, entry.getValue());
                    preparedStatement.executeUpdate();
                    System.out.println("Inserted/Updated productId: " + entry.getKey() + " with quantity: " + entry.getValue());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions
        }
    }

    public Cart getCart(int userId) {
        Cart cart = new Cart();
        String query = "SELECT product_id, quantity FROM carts WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");
                cart.addItem(productId, quantity); // Add items to the cart
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions
        }
        return cart; // Return the cart with products
    }

    public void clearCart(int userId) {
        String query = "DELETE FROM carts WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions
        }
    }

    
    public void removeItemFromCart(int userId, int productId) {
        String query = "DELETE FROM carts WHERE user_id = ? AND product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            int rowsAffected = preparedStatement.executeUpdate(); // Execute update
            if (rowsAffected > 0) {
                System.out.println("Removed productId: " + productId + " from userId: " + userId);
            } else {
                System.out.println("No product found with ID: " + productId + " for user ID: " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions
        }
    }

}