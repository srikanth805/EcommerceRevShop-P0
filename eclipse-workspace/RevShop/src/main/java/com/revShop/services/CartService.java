package com.revShop.services;

import com.revShop.dao.CartDAO;
import com.revShop.models.Cart;
import com.revShop.models.Product;

import java.util.List;
import java.util.Optional;

public class CartService {
    private CartDAO cartDAO;
    private Cart cart;
    private int userId; // Store userId for the current session
    private List<Product> availableProducts; // List of available products

    // Constructor for CartService, initializes with userId and available products
    public CartService(int userId, List<Product> availableProducts) {
        this.cartDAO = new CartDAO();
        this.userId = userId;
        this.availableProducts = availableProducts;
        this.cart = cartDAO.getCart(userId); 
        
        // Initialize the cart if it doesn't exist for the user
        if (this.cart == null) {
            this.cart = new Cart(userId); // Use the constructor with userId in Cart
        }
    }

    // Add item to the cart
    public boolean addItemToCart(int productId, int quantity) {
        // Validate quantity
        if (quantity <= 0) {
            System.out.println("Invalid quantity specified.");
            return false;
        }

        // Check if the product is available in the available products list
        Optional<Product> productOpt = availableProducts.stream()
                .filter(p -> p.getId() == productId)
                .findFirst();

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            cart.addItem(productId, quantity); // Add the product to the cart
            cartDAO.saveCart(cart, userId); // Save the updated cart in the DB
            return true;
        } else {
            System.out.println("Product with ID " + productId + " not available.");
            return false;
        }
    }

    // Remove item from cart
    public void removeItemFromCart(int productId) {
        if (cart.hasProduct(productId)) {  // Check if the cart has the product
            cart.removeItem(productId); // Remove the product from the cart
            cartDAO.removeItemFromCart(userId, productId); // Save the updated cart in the DB
            
        } else {
            System.out.println("Product not found in cart.");
            
        }
    }
  
  

    // Get the cart for the current user
    public Cart getCart() {
        return cart;
    }

    // Return products in the cart along with available product info (quantity, etc.)
    public List<Product> getCartForUser() {
        return cart.getProducts(availableProducts);  // Return products based on available products
    }

    // Clear the cart for the current user
    public void clearCart() {
        cart.clearCart(); // Clear the cart instance
        cartDAO.clearCart(userId); // Remove the cart from the database
    }
}