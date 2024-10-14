package com.revShop.dao;

import com.revShop.models.Product;
import com.revShop.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO {
    public Product getProductById(int id) {
        Product product = null;

        String query = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); // Your DB connection method
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImageUrl(rs.getString("imageUrl")); // Adjust based on your DB structure
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log your exception or handle it as needed
        }

        return product;
    }
    public List<Product> searchProducts(String query) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ?"; // SQL query to search products

        try (Connection conn =DatabaseConnection. getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                // Populate product object with data from the result set
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("imageUrl"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log any exceptions
        }

        return products;
    }

    // Save a new product to the database
	public void saveProduct(Product product) {
	    String sql = "INSERT INTO products (name, price, description, quantity, imageUrl, user_id) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        // Set the values from the Product object
	        statement.setString(1, product.getName());
	        statement.setDouble(2, product.getPrice());
	        statement.setString(3, product.getDescription());
	        statement.setInt(4, product.getQuantity()); // Set quantity before image URL
	        statement.setString(5, product.getImageUrl());
	        statement.setInt(6, product.getUserId());

	        // Execute the update
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("A new product was inserted successfully!");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error inserting product: " + e.getMessage());
	    }
	}


    // Retrieve all products from the database
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setUserId(resultSet.getInt("user_id"));
                product.setImageUrl(resultSet.getString("imageUrl"));
                productList.add(product);
            }
            
            System.out.println("Retrieved " + productList.size() + " products from the database."); 
            
        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }
        
        return productList;
    }


    // Find a product by its ID
    public Optional<Product> findProductById(int id) {
        String query = "SELECT * FROM products WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setUserId(resultSet.getInt("user_id"));
                product.setImageUrl(resultSet.getString("imageUrl"));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            System.out.println("Error finding product by ID: " + e.getMessage());
        }
        return Optional.empty(); // Return empty if not found
    }

    // Find a product by its ID and seller ID
    public Optional<Product> findProductByIdAndSellerId(int productId, int sellerId) {
        String query = "SELECT * FROM products WHERE id = ? AND user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, sellerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setUserId(resultSet.getInt("user_id"));
                product.setImageUrl(resultSet.getString("imageUrl"));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            System.out.println("Error finding product by ID and seller ID: " + e.getMessage());
        }
        return Optional.empty(); // Return empty if not found
    }

    // Delete a product by its ID
    public void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }

    // Update a product in the database
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, description = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, product.getId());
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Updating product failed, no rows affected.");
            }
        }
    }


    // Retrieve available products from the database
    public List<Product> getAvailableProducts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE quantity>0"; // Ensure 'available' field exists
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setUserId(resultSet.getInt("user_id"));
                product.setImageUrl(resultSet.getString("imageUrl"));
                productList.add(product);
            }
            System.out.println("Retrieved " + productList.size() + " available products from the database.");
        } catch (SQLException e) {
            System.out.println("Error fetching available products: " + e.getMessage());
        }
        return productList;
    }

    // Check if a product exists by its name
    public boolean doesProductExist(String productName) {
        String query = "SELECT COUNT(*) FROM products WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Return true if count is greater than 0
            }
        } catch (SQLException e) {
            System.out.println("Error checking if product exists: " + e.getMessage());
        }
        return false; // Return false if an exception occurs or product doesn't exist
    }

    // Method to retrieve products by seller ID
    public List<Product> getProductsBySellerId(int sellerId) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE user_id = ?"; // Assuming user_id is the seller ID
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sellerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setUserId(resultSet.getInt("user_id"));
                product.setImageUrl(resultSet.getString("imageUrl"));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products by seller ID: " + e.getMessage());
        }
        return productList; // Return the list of products for the seller
    }
}