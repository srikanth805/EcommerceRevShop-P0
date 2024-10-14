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
import com.revShop.models.Order; // Import the Order model to pass to the JSP
import com.revShop.services.CartService;
import com.revShop.services.OrderService;
import com.revShop.services.ProductService;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private ProductService productService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService(); // Initialize ProductService
        orderService = new OrderService();     // Initialize OrderService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if user is not logged in
            return;
        }

        CartService cartService = new CartService(userId, productService.getAvailableProducts());
        List<Product> cartItems = cartService.getCartForUser();
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/WEB-INF/Checkout.jsp").forward(request, response); // Forward to checkout page
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CartService cartService = new CartService(userId, productService.getAvailableProducts());
        List<Product> cartItems = cartService.getCartForUser();

        if (cartItems.isEmpty()) {
            request.setAttribute("message", "Your cart is empty. Please add items before checking out.");
            request.getRequestDispatcher("/WEB-INF/Checkout.jsp").forward(request, response);
            return;
        }

        boolean orderPlaced = orderService.placeOrder(userId, cartItems);
        if (orderPlaced) {
            cartService.clearCart();

            // Get the list of all orders for the user to show in order history
            List<Order> userOrders = orderService.getOrdersByUserId(userId);
            request.setAttribute("orders", userOrders);

            // Forward to the order history page with the orders
            request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Failed to place the order. Please try again.");
            request.getRequestDispatcher("/WEB-INF/Checkout.jsp").forward(request, response);
        }
    }
}