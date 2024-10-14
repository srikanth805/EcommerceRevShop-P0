package com.revShop.services;

import com.revShop.dao.OrderDAO;
import com.revShop.dao.CartDAO;
import com.revShop.models.Order;
import com.revShop.models.Product;

import java.util.List;

public class CheckoutService {
    private OrderDAO orderDAO;
    private CartDAO cartDAO;

    public CheckoutService() {
        this.orderDAO = new OrderDAO();
        this.cartDAO = new CartDAO();
    }

    // Method to handle checkout process
    public void checkout(int userId, List<Product> products, double totalAmount) {
        // Save the order
        Order order = new Order();
        order.setUserId(userId);
        order.setProducts(products);
        order.setTotalAmount(totalAmount);
        orderDAO.saveOrder(order);

        // Clear the user's cart after successful order placement
        cartDAO.clearCart(userId);
    }
}
