package com.revShop.services;

import com.revShop.dao.ReviewDAO;
import com.revShop.models.Review;

import java.util.List;

public class ReviewService {
    private ReviewDAO reviewDAO;

    public ReviewService() {
        this.reviewDAO = new ReviewDAO();
    }

    // Method to save a review
    public void saveReview(Review review) {
        reviewDAO.saveReview(review);
    }

    // Method to get all reviews for a specific product
    public List<Review> getReviewsForProduct(int productId) {
        return reviewDAO.getReviewsForProduct(productId);
    }

    // Method to delete a review by ID
    public void deleteReview(int id) {
        reviewDAO.deleteReview(id);
    }
}
