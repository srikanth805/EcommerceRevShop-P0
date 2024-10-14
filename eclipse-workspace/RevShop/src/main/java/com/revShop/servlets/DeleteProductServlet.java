package com.revShop.servlets;

import com.revShop.services.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    private final ProductService productService;

    public DeleteProductServlet() {
        this.productService = new ProductService(); // Initialize ProductService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id")); // Get product ID
            productService.deleteProduct(productId); // Delete product by ID
            response.sendRedirect("productList"); // Redirect to the product list
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting product");
        }
    }
}