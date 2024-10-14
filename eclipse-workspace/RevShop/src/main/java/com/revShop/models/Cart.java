package com.revShop.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    private int userId; // Store the userId associated with the cart
    private Map<Integer, Integer> items; // Product ID and quantity

    // Constructor to initialize cart with a userId
    public Cart(int userId) {
        this.userId = userId;
        this.items = new HashMap<>();
    }

    // Default constructor (if needed)
    public Cart() {
        this.items = new HashMap<>();
    }

    // Add an item to the cart
    public void addItem(int productId, int quantity) {
        items.put(productId, items.getOrDefault(productId, 0) + quantity);
    }

    // Remove an item from the cart
    public void removeItem(int productId) {
        items.remove(productId);
    }
    

    // Check if the cart contains a specific product
    public boolean hasProduct(int productId) {
        return items.containsKey(productId);
    }

    // Get all items in the cart (Product ID and quantity)
    public Map<Integer, Integer> getItems() {
        return items;
    }

    // Clear the cart (removes all items)
    public void clearCart() {
        items.clear();
    }

    // Get products in the cart based on available products
    public List<Product> getProducts(List<Product> availableProducts) {
        return items.entrySet().stream()
                .map(entry -> {
                    int productId = entry.getKey();
                    return availableProducts.stream()
                            .filter(product -> product.getId() == productId)
                            .findFirst()
                            .orElse(null); // Return null if the product is not found
                })
                .filter(product -> product != null) // Filter out nulls
                .collect(Collectors.toList());
    }

    // Get the userId associated with the cart
    public int getUserId() {
        return userId;
    }
}