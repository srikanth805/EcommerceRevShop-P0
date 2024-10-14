package com.revShop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.revShop.models.Product;
import com.revShop.services.CartService;
import com.revShop.services.ProductService;

@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {
    private CartService cartService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService(); // Initialize ProductService
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId"); // Get userId from session
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Redirect if user is not logged in
            return;
        }

        String productIdStr = request.getParameter("productId");
        if (productIdStr != null) {
            try {
                int productId = Integer.parseInt(productIdStr);
                List<Product> availableProducts = productService.getAvailableProducts(); // Fetch available products
                cartService = new CartService(userId, availableProducts); // Initialize CartService
                cartService.removeItemFromCart(productId); // Remove item from cart
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Handle invalid number format
            }
        }

        response.sendRedirect("cart"); // Redirect to cart after removal
    }
}