package com.revShop.dao;

import com.revShop.models.Order;
import com.revShop.models.Product;
import com.revShop.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // Save the order and return the generated order ID
    public int saveOrder(Order order) {
        String query = "INSERT INTO orders (userId, orderDate, totalAmount) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            preparedStatement.setDouble(3, order.getTotalAmount());
            preparedStatement.executeUpdate();

            // Get the generated order ID
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Return the generated order ID
            }
        } catch (SQLException e) {
            // Log the exception (consider using a logging framework)
            System.err.println("Error saving order: " + e.getMessage());
        }
        return -1; // Return -1 if there's an issue
    }
 // Add this method to the OrderDAO class
 // Method to get orders by user ID
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE userId = ?"; // Fetch orders for a specific user
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userId"));
                order.setOrderDate(new Date(resultSet.getTimestamp("orderDate").getTime())); // Convert Timestamp to Date
                order.setTotalAmount(resultSet.getDouble("totalAmount"));
                order.setProducts(getProductsForOrder(order.getId())); // Fetch products for the order
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching orders by user ID: " + e.getMessage());
        }
        return orderList;
    }


    // Save the products associated with an order
    public void saveOrderProducts(int orderId, List<Product> products) {
        String query = "INSERT INTO order_products (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection()) {
            for (Product product : products) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, orderId);
                    preparedStatement.setInt(2, product.getId());
                    preparedStatement.setInt(3, product.getQuantity()); // Use actual quantity from the Product object
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            // Log the exception (consider using a logging framework)
            System.err.println("Error saving order products: " + e.getMessage());
        }
    }

    // Fetch all orders
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userId"));
                order.setOrderDate(resultSet.getDate("orderDate"));
                order.setTotalAmount(resultSet.getDouble("totalAmount"));
                order.setProducts(getProductsForOrder(order.getId())); // Fetch products for the order
                orderList.add(order);
            }
        } catch (SQLException e) {
            // Log the exception
            System.err.println("Error fetching all orders: " + e.getMessage());
        }
        return orderList;
    }

    // Fetch a single order by ID
    public Order findOrderById(int id) {
        String query = "SELECT * FROM orders WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userId"));
                order.setOrderDate(resultSet.getDate("orderDate"));
                order.setTotalAmount(resultSet.getDouble("totalAmount"));
                order.setProducts(getProductsForOrder(order.getId())); // Fetch products for the order
                return order;
            }
        } catch (SQLException e) {
            // Log the exception
            System.err.println("Error finding order by ID: " + e.getMessage());
        }
        return null;
    }

    // Delete an order by ID
    public boolean deleteOrder(int id) {
        String query = "DELETE FROM orders WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0; // Return true if deleted
        } catch (SQLException e) {
            // Log the exception
            System.err.println("Error deleting order: " + e.getMessage());
        }
        return false; // Return false if there's an issue
    }

    // Fetch products for a given order
    private List<Product> getProductsForOrder(int orderId) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.price, p.description, op.quantity " + // Fetch quantity as well
                       "FROM order_products op " +
                       "JOIN products p ON op.product_id = p.id " +
                       "WHERE op.order_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(); // Creating a new Product instance
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity")); // Set quantity fetched from DB
                productList.add(product);
            }
        } catch (SQLException e) {
            // Log the exception
            System.err.println("Error fetching products for order: " + e.getMessage());
        }
        return productList;
    }
}
