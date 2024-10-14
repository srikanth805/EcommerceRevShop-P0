package com.revShop.models;


public class Review {
    private int id;             // Unique identifier for the review
    private int productId;      // ID of the product being reviewed
    private int userId;         // ID of the user who wrote the review
    private String comment;      // Review comment
    private int rating;          // Rating given by the user (1-5)

    // Constructor
    public Review(int id, int productId, int userId, String comment, int rating) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
    }

    // Default constructor
    public Review() {
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}

