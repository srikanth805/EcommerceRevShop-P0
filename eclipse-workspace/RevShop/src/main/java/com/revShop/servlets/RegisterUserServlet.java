package com.revShop.servlets;

import com.revShop.models.User;
import com.revShop.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {
    private final UserService userService;

    public RegisterUserServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role"); // Ensure this is part of your registration form

        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // Store plain password
        user.setRole(role);

        try {
            if (userService.registerUser(email, password, role)) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("errorMessage", "Email already exists.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during registration.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
