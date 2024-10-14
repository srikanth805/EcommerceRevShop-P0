package com.revShop.test;

import com.revShop.models.Product;
import com.revShop.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderTest {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        // Create sample products with quantities, userId, and image URLs
        Product product1 = new Product(1, "Laptop", 1200.00, "High-performance laptop", 1, 1, "images/laptop.jpeg"); // Add imageUrl
        Product product2 = new Product(2, "Mouse", 25.99, "Wireless mouse", 2, 1, "images/mouse.jpeg"); // Add imageUrl, Quantity 2
        Product product3 = new Product(3, "Keyboard", 50.00, "Mechanical keyboard", 1, 1, "images/keyboard.jpeg"); // Add imageUrl

        // Add products to a list
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        // Place an order for a user (with userId 1, for example)
        orderService.placeOrder(1, productList); // Total can be recalculated if needed

        // Optionally, print out all orders to verify the data
        System.out.println("All Orders:");
        orderService.getAllOrders().forEach(System.out::println);
    }
}
