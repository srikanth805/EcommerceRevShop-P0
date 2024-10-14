package com.revShop.servlets;

import com.revShop.dao.CartDAO;
import com.revShop.dao.ProductDAO;
import com.revShop.models.Cart;
import com.revShop.models.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/confirmOrder")
public class ConfirmOrderServlet extends HttpServlet {
    private CartDAO cartDAO = new CartDAO();
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        if (userIdParam == null || userIdParam.isEmpty()) {
            request.setAttribute("errorMessage", "User ID is required."); // Add an appropriate error message
            request.getRequestDispatcher("error.jsp").forward(request, response); // Forward to an error page
            return; // Exit the method
        }
        int userId = Integer.parseInt(userIdParam); // This should now be safe
        Cart cart = cartDAO.getCart(userId); // Get the cart for the user
        
        // Fetch available products
        List<Product> availableProducts = productDAO.getAvailableProducts(); // Make sure this method exists

        try {
            // Get products in the cart
            List<Product> productsInCart = cart.getProducts(availableProducts);
            // Proceed with the order confirmation logic
            request.setAttribute("productsInCart", productsInCart);
            // Forward to confirmation JSP page
            request.getRequestDispatcher("confirmOrder.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Handle error
            request.setAttribute("errorMessage", "An error occurred while confirming the order.");
            request.getRequestDispatcher("error.jsp").forward(request, response); // Forward to an error page
        }
    }

}