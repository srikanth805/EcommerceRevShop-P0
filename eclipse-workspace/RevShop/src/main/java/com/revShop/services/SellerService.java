package com.revShop.services;

import com.revShop.dao.ProductDAO;
import com.revShop.models.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SellerService {
    private final ProductDAO productDAO;

    public SellerService() {
        this.productDAO = new ProductDAO();
    }

    public boolean addProduct(String name, double price, String description,int quantity, String imageUrl) throws SQLException {
        int currentUserId = getCurrentUserId(); // Retrieve the current user's ID

        if (productDAO.doesProductExist(name)) {
            System.out.println("Product '" + name + "' already exists. Skipping addition.");
            return false;
        }

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setQuantity(quantity);
        
        product.setImageUrl(imageUrl); // Set the image URL
        product.setUserId(currentUserId);
        // Set the user ID for the product

        productDAO.saveProduct(product); // Pass the complete product object
        System.out.println("Product '" + name + "' added successfully.");
        return true;
    }

    public boolean updateProduct(int id, String name, double price, String description) throws SQLException {
        Optional<Product> existingProductOpt = productDAO.findProductById(id);
        if (existingProductOpt.isEmpty()) {
            System.out.println("Product with ID " + id + " does not exist. Cannot update.");
            return false;
        }

        Product existingProduct = existingProductOpt.get();
        existingProduct.setName(name);
        existingProduct.setPrice(price);
        existingProduct.setDescription(description);

        productDAO.updateProduct(existingProduct); // Update with existing product
        System.out.println("Product with ID " + id + " updated successfully.");
        return true;
    }

    public boolean deleteProduct(int id) throws SQLException {
        Optional<Product> existingProductOpt = productDAO.findProductById(id);
        if (existingProductOpt.isEmpty()) {
            System.out.println("Product with ID " + id + " does not exist. Cannot delete.");
            return false;
        }

        productDAO.deleteProduct(id); // Delete by ID
        System.out.println("Product with ID " + id + " deleted successfully.");
        return true;
    }

    public List<Product> listAllProducts() throws SQLException {
        return productDAO.getAllProducts(); // Fetch all products from the DAO
    }

    // Implement this method based on your authentication logic
    private int getCurrentUserId() {
        // Logic to retrieve the current user's ID from the session or authentication context
        // Example:
        // HttpSession session = request.getSession();
        // User user = (User) session.getAttribute("user");
        // return user.getId();

        // For now, return a dummy user ID (for example purposes only)
        return 1; // Replace this with the actual logic
    }
}