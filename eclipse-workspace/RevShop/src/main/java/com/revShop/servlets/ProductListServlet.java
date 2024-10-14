package com.revShop.servlets;

import com.revShop.models.Product;
import com.revShop.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
    private final ProductService productService;

    public ProductListServlet() {
        this.productService = new ProductService(); // Initialize ProductService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String searchQuery = request.getParameter("search"); // Get search query parameter from the request
            List<Product> products;

            // Check if the search query is provided
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                System.out.println("Searching products with query: " + searchQuery); // Debug line
                // Fetch filtered products based on the search query
                products = productService.searchProducts(searchQuery);
            } else {
                System.out.println("Fetching all products..."); // Debug line
                // Fetch all products if no search query is provided
                products = productService.getAvailableProducts();
            }

            System.out.println("Number of products retrieved: " + products.size()); // Debug output

            // Set the product list as a request attribute
            request.setAttribute("products", products);

            // Forward to the product list JSP page
            request.getRequestDispatcher("/productList.jsp").forward(request, response);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to retrieve products."); // Set error message
            request.getRequestDispatcher("error.jsp").forward(request, response); // Forward to an error page
        }
    }
}
