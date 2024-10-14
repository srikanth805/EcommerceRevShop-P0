package com.revShop.services;

import com.revShop.dao.OrderDAO;
import com.revShop.models.Order;
import com.revShop.models.Product;

import java.util.Date;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    // Method to place an order
    public boolean placeOrder(int userId, List<Product> products) {
        // Calculate total amount based on product price and quantity
        double totalAmount = products.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity()) // Ensure getQuantity() exists
                .sum();
        
        Order order = new Order();
        order.setUserId(userId);
        order.setProducts(products);
        order.setOrderDate(new Date());
        order.setTotalAmount(totalAmount);

        // Step 1: Save the order and get the generated orderId
        int orderId = orderDAO.saveOrder(order);
        
        if (orderId != -1) { // If order saved successfully
            // Step 2: Save products for the order
            orderDAO.saveOrderProducts(orderId, products);
            return true; // Return true if order placement was successful
        } else {
            System.out.println("Error placing order.");
            return false; // Return false if order placement failed
        }
    }

    // Method to get all orders
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    // Method to find an order by ID
    public Order findOrderById(int id) {
        return orderDAO.findOrderById(id);
    }

    // Method to delete an order
    public void deleteOrder(int id) {
        orderDAO.deleteOrder(id);
    }
    public List<Order> getOrdersByUserId(int userId) {
        return orderDAO.getOrdersByUserId(userId); // Delegate to DAO
    }
}
