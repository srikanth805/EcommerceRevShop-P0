package com.revShop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.revShop.models.Review;
import com.revShop.services.ReviewService;

@WebServlet("/reviewsss")
public class SubmitReviewServlet extends HttpServlet {
    private ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        reviewService = new ReviewService(); // Initialize ReviewService
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId"); // Get userId from session

        if (userId != null) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String comment = request.getParameter("comment");
            int rating = Integer.parseInt(request.getParameter("rating"));

            // Create Review object and save it
            Review review = new Review();
            review.setProductId(productId);
            review.setUserId(userId);
            review.setComment(comment);
            review.setRating(rating);
            reviewService.saveReview(review);

            response.sendRedirect("productdetails?productId=" + productId); // Redirect back to product details
        } else {
            response.sendRedirect("buyerDashboard.jsp"); // Redirect if user is not logged in
        }
    }
}
