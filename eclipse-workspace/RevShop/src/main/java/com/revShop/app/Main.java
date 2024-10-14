package com.revShop.app;

import com.revShop.models.Product;
import com.revShop.models.User;
import com.revShop.services.UserService;
import com.revShop.services.ProductService;
import com.revShop.ui.SellerInterface;
import com.revShop.ui.CartInterface;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            UserService userService = new UserService();

            // Registering a user with a new email
            String email = "uijni@example.com"; // New email
            String password = "root";
            String role = "buyer";

            boolean isRegistered = userService.registerUser(email, password, role);
            System.out.println(isRegistered ? "User registered successfully." : "User registration failed.");

            // Logging in
            User loggedInUser = null;
            try {
                loggedInUser = userService.loginUser(email, password);
            } catch (SQLException e) {
                System.err.println("SQL Exception occurred during login: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An error occurred during login: " + e.getMessage());
            }

            if (loggedInUser != null) {
                System.out.println("User logged in successfully. User ID: " + loggedInUser.getId());

                // Proceed to get user ID if logged in
                int userId = loggedInUser.getId();

                // Product service usage
                ProductService productService = new ProductService();

                // Adding new products (with image URLs, updated parameter order)
                productService.addProduct("Wireless Mouse", 29.99, "Ergonomic wireless mouse for comfortable usage", 10, userId, "https://example.com/images/wireless-mouse.jpg");
                productService.addProduct("Bluetooth Speaker", 49.99, "Portable Bluetooth speaker with excellent sound quality", 15, userId, "https://example.com/images/bluetooth-speaker.jpg");
                productService.addProduct("4K Monitor", 399.99, "Ultra HD monitor with vibrant colors and stunning clarity", 5, userId, "https://example.com/images/4k-monitor.jpg");
                productService.addProduct("Gaming Headset", 79.99, "Comfortable gaming headset with surround sound", 8, userId, "https://example.com/images/gaming-headset.jpg");
                productService.addProduct("Mechanical Keyboard", 99.99, "RGB mechanical keyboard for gamers and typists", 12, userId, "https://example.com/images/mechanical-keyboard.jpg");
                productService.addProduct("Action Camera", 249.99, "Compact action camera for adventure seekers", 7, userId, "https://example.com/images/action-camera.jpg");
                productService.addProduct("Smart Home Hub", 89.99, "Smart home hub to control all your devices", 20, userId, "https://example.com/images/smart-home-hub.jpg");
                productService.addProduct("External Hard Drive", 129.99, "1TB external hard drive for backup and storage", 10, userId, "https://example.com/images/external-hard-drive.jpg");
                productService.addProduct("Fitness Tracker", 59.99, "Wearable fitness tracker to monitor your health and activity", 30, userId, "https://example.com/images/fitness-tracker.jpg");
                productService.addProduct("VR Headset", 299.99, "Virtual reality headset for an immersive experience", 6, userId, "https://example.com/images/vr-headset.jpg");

                // Listing all products
                List<Product> availableProducts = productService.getAllProducts(); // Fetch the list of products
                System.out.println("All Products:");
                availableProducts.forEach(product -> {
                    System.out.println("Product Name: " + product.getName() + ", Price: $" + product.getPrice() +
                            ", Description: " + product.getDescription() + ", Image URL: " + product.getImageUrl() +
                            ", Quantity: " + product.getQuantity());
                });

                // Cart functionality
                CartInterface cartInterface = new CartInterface(userId, availableProducts); // Pass userId and available products
                cartInterface.showMenu(); // Start the cart interface menu

                // Optional: Integrate seller functionalities through the SellerInterface
                SellerInterface sellerInterface = new SellerInterface(); // Adjust if parameters are needed
                sellerInterface.showMenu(); // Start the seller interface menu
            } else {
                System.out.println("User login failed.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}