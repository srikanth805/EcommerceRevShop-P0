package com.revShop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.revShop.models.Product;
import com.revShop.models.Review;
import com.revShop.services.ProductService;
import com.revShop.services.ReviewService;

@WebServlet("/productdetails")
public class ProductDetailsServlet extends HttpServlet {
    private ProductService productService;
    private ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        reviewService = new ReviewService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");

        // Check for missing productId parameter
        if (productIdStr == null || productIdStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Product ID is missing.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            return;
        }

        int productId;
        // Attempt to parse the productId
        try {
            productId = Integer.parseInt(productIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Product ID format.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            return;
        }

        // Fetch the product by ID
        Optional<Product> productOptional = productService.findProductById(productId);
        if (productOptional.isEmpty()) {
            request.setAttribute("errorMessage", "Product not found.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            return;
        }

        Product product = productOptional.get();
        request.setAttribute("product", product);

        // Fetch reviews for the product
        List<Review> reviews = reviewService.getReviewsForProduct(productId);
        request.setAttribute("reviews", reviews);

        // Forward to product details page
        request.getRequestDispatcher("/WEB-INF/productdetails.jsp").forward(request, response);
    }
}