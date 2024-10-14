package com.revShop.servlets;

import com.revShop.models.User;
import com.revShop.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userService.loginUser(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                
                // Set user ID, email, and role in the session
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getEmail()); // Using email as username
                session.setAttribute("userRole", user.getRole());  // Set the user role in session

                // Redirect based on user role
                if ("buyer".equals(user.getRole())) {
                    response.sendRedirect("buyerDashboard.jsp");
                } else if ("seller".equals(user.getRole())) {
                    response.sendRedirect("sellerDashboard.jsp");
                } else {
                    response.sendRedirect("login.jsp?error=Invalid role");
                }
            } else {
                // Login failed, redirect to login page with error
                response.sendRedirect("login.jsp?error=Invalid email or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=An error occurred during login");
        }
    }
}
