package com.revShop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.revShop.services.CartService;
import com.revShop.models.Product;
import com.revShop.services.ProductService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private CartService cartService;
    private ProductService productService; // Declare ProductService

    @Override
    public void init() throws ServletException {
        // Initialize ProductService
        productService = new ProductService(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId"); // Get userId from session
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Redirect if user is not logged in
            return;
        }

        // Fetch available products and initialize CartService
        List<Product> availableProducts = productService.getAvailableProducts(); 
        cartService = new CartService(userId, availableProducts); // Initialize CartService

        // Retrieve cart items for the user
        request.setAttribute("cartItems", cartService.getCartForUser());
        request.setAttribute("availableProducts", availableProducts); // Add available products to request
        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId"); // Ensure user is logged in
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Redirect if user is not logged in
            return;
        }

        String action = request.getParameter("action"); // Get action (add/remove)
        String productIdStr = request.getParameter("productId");

        if (productIdStr != null) {
            try {
                int productId = Integer.parseInt(productIdStr);

                // Reinitialize CartService for the current user
                List<Product> availableProducts = productService.getAvailableProducts();
                cartService = new CartService(userId, availableProducts); 

                if ("remove".equals(action)) {
                    // Handle item removal
                    cartService.removeItemFromCart(productId); // Remove item from cart
                } else {
                    // Handle item addition (default action)
                    String quantityStr = request.getParameter("quantity");
                    if (quantityStr != null) {
                        int quantity = Integer.parseInt(quantityStr);
                        if (quantity > 0) {  // Ensure quantity is positive
                            cartService.addItemToCart(productId, quantity); // Add item to the cart
                        } else {
                            System.out.println("Quantity must be greater than 0.");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Handle invalid number format
            }
        }

        response.sendRedirect("cart"); // Redirect to cart after operation
    }
}