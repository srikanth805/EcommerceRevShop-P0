package com.revShop.ui;

import com.revShop.services.SellerService;
import com.revShop.models.Product;

import java.sql.SQLException;
import java.util.Scanner;

public class SellerInterface {
    private SellerService sellerService;
    private Scanner scanner;

    public SellerInterface() {
        this.sellerService = new SellerService();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("=== Seller Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. List Products");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        updateProduct();
                        break;
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        listProducts();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void addProduct() throws SQLException {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        int quantity = scanner.nextInt(); // Read quantity
        scanner.nextLine();
        System.out.print("Enter image file name (with path if needed): ");
        String imageUrl = scanner.nextLine(); // Capture the image URL or path

        boolean isAdded = sellerService.addProduct(name, price, description,quantity, imageUrl); // Pass imageUrl
        if (isAdded) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product already exists or failed to add.");
        }
    }

    private void updateProduct() throws SQLException {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new product description: ");
        String description = scanner.nextLine();

        boolean isUpdated = sellerService.updateProduct(id, name, price, description); 
        if (isUpdated) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Failed to update product. It may not exist.");
        }
    }

    private void deleteProduct() throws SQLException {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean isDeleted = sellerService.deleteProduct(id);
        if (isDeleted) {
            System.out.println("Product with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Failed to delete product with ID " + id + ". It may not exist.");
        }
    }

    private void listProducts() throws SQLException {
        System.out.println("Listing all products:");
        for (Product product : sellerService.listAllProducts()) {
            System.out.println("Product ID: " + product.getId() + 
                               ", Name: " + product.getName() + 
                               ", Price: $" + product.getPrice() + 
                               ", Description: " + product.getDescription());
        }
    }

    private int getCurrentUserId() {
        // Implement this method to return the current user's ID
        // For example, you might retrieve this from the session
        return 1; // Temporary placeholder
    }
}