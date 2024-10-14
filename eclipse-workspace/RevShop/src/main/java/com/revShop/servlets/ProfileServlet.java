package com.revShop.servlets;

import java.io.IOException;

import com.revShop.models.User;
import com.revShop.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
	public class ProfileServlet extends HttpServlet {
	    private UserService userService = new UserService();

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession(false);

	        if (session != null && session.getAttribute("userId") != null) {
	            int userId = (int) session.getAttribute("userId");

	            // Fetch user details using the userId
	            User user = userService.getUserById(userId);

	            if (user != null) {
	                request.setAttribute("user", user);
	                request.getRequestDispatcher("/profile.jsp").forward(request, response);
	            } else {
	                response.sendRedirect("login.jsp"); // If no user found, redirect to login
	            }
	        } else {
	            response.sendRedirect("login.jsp"); // If not logged in, redirect to login
	        }
	    }
	}



