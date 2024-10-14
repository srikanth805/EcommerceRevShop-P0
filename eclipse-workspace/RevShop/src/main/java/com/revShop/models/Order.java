package com.revShop.models;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;                   // Unique identifier for the order
    private int userId;              // ID of the user who placed the order
    private List<Product> products;   // List of products in the order
    private Date orderDate;           // Date when the order was placed
    private double totalAmount;        // Total amount of the order

    // Constructor
    public Order(int id, int userId, List<Product> products, Date orderDate, double totalAmount) {
        this.id = id;
        this.userId = userId;
        this.products = products;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Default constructor
    public Order() {
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", products=" + products +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
