package com.revShop.ui;

import com.revShop.models.Product;
import com.revShop.services.CartService;
import com.revShop.services.CheckoutService;

import java.util.List;
import java.util.Scanner;

public class CartInterface {
    private CartService cartService;
    private CheckoutService checkoutService;
    private Scanner scanner;
    private int userId; // Assuming userId is set when a user logs in

    public CartInterface(int userId, List<Product> availableProducts) {
        this.cartService = new CartService(userId, availableProducts); // Pass userId and availableProducts to CartService
        this.checkoutService = new CheckoutService();
        this.scanner = new Scanner(System.in);
        this.userId = userId;
    }

    public void showMenu() {
        while (true) {
            System.out.println("=== Cart Menu ===");
            System.out.println("1. Add Item to Cart");
            System.out.println("2. Remove Item from Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addItemToCart();
                    break;
                case 2:
                    removeItemFromCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    System.out.println("Exiting Cart Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addItemToCart() {
        System.out.print("Enter product ID to add to cart: ");
        int productId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        cartService.addItemToCart(productId, quantity);
        System.out.println("Item added to cart.");
    }

    private void removeItemFromCart() {
        System.out.print("Enter product ID to remove from cart: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        cartService.removeItemFromCart(productId);
        System.out.println("Item removed from cart.");
    }

    private void viewCart() {
        System.out.println("Cart Items:");
        cartService.getCart().getItems().forEach((id, qty) -> {
            System.out.println("Product ID: " + id + ", Quantity: " + qty);
        });
    }

    // Updated checkout method
    private void checkout() {
        List<Product> products = cartService.getCartForUser(); // Now this works correctly
        if (products.isEmpty()) {
            System.out.println("Your cart is empty. Please add items before checking out.");
            return;
        }

        // Calculate the total amount
        double totalAmount = products.stream().mapToDouble(Product::getPrice).sum();

        // Perform the checkout
        checkoutService.checkout(userId, products, totalAmount);
        System.out.println("Checkout completed successfully. Your order has been placed.");

        // Clear the cart after checkout
        cartService.clearCart();
        System.out.println("Your cart has been cleared.");
    }
}
