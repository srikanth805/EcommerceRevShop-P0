package com.revShop.servlets;

import com.revShop.dao.OrderDAO;
import com.revShop.models.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders") // Define the URL pattern
public class OrderViewServlet extends HttpServlet {
    private OrderDAO orderDAO;

    @Override
    public void init() {
        // Initialize the OrderDAO
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all orders from the database
        List<Order> orderList = orderDAO.getAllOrders();

        // Set the order list as a request attribute
        request.setAttribute("orderList", orderList);

        // Forward the request to the orderDetails.jsp page
        request.getRequestDispatcher("orderDetails.jsp").forward(request, response);
    }
}
