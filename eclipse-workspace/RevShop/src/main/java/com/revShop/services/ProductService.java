package com.revShop.services;

import com.revShop.dao.ProductDAO;
import com.revShop.models.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public boolean addProduct(String name, double price, String description, int userId, int quantity, String imageUrl) throws SQLException {
        // Check if the product already exists
        if (productDAO.doesProductExist(name)) {
            System.out.println("Product already exists. Please use a different name.");
            return false; // Indicate that the product was not added
        }

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setUserId(userId); // Set user ID correctly
        product.setQuantity(quantity); // Set quantity
        product.setImageUrl(imageUrl); // Set image URL

        productDAO.saveProduct(product); // Pass the product object
        System.out.println("Product added successfully.");
        return true;
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }
    public List<Product> searchProducts(String query) {
        return productDAO.searchProducts(query);
        }

    public Optional<Product> findProductById(int id) {
        return productDAO.findProductById(id); // Use Optional to avoid null returns
    }

    public Optional<Product> findProductByIdAndSellerId(int productId, int sellerId) throws SQLException {
        return productDAO.findProductByIdAndSellerId(productId, sellerId);
    }

    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product); // Use the DAO to perform the update
    }

    public List<Product> getAvailableProducts() {
        return productDAO.getAvailableProducts(); // Fetch available products from DAO
    }



    public void deleteProduct(int productId) throws SQLException {
        productDAO.deleteProduct(productId); // Use the DAO to delete product
    }

    public List<Product> findProductsBySellerId(int sellerId) throws SQLException {
        return productDAO.getProductsBySellerId(sellerId); // Call the DAO method
    }
}