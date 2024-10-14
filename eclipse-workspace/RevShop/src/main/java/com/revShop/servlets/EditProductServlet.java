package com.revShop.servlets;

import com.revShop.models.Product;
import com.revShop.dao.ProductDAO; // Ensure you import your ProductDAO
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editproducts")
public class EditProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Integer userId = (Integer) session.getAttribute("userId"); // Get user ID from session
	    String userRole = (String) session.getAttribute("userRole"); // Get user role from session

	    if (userId == null || !"seller".equals(userRole)) {
	        // User not logged in or not a seller, redirect to login
	        response.sendRedirect("login.jsp");
	        return;
	    }

	    // Retrieve product ID from request
	    String id = request.getParameter("id");

	    // Input validation for product ID
	    if (id == null || id.isEmpty()) {
	        request.setAttribute("errorMessage", "Product ID is required.");
	        request.getRequestDispatcher("productList.jsp").forward(request, response);
	        return;
	    }

	    ProductDAO productDAO = new ProductDAO();
	    Product product;

	    try {
	        // Fetch product by ID
	        product = productDAO.getProductById(Integer.parseInt(id));

	        if (product == null) {
	            request.setAttribute("errorMessage", "Product not found.");
	            request.getRequestDispatcher("productList.jsp").forward(request, response);
	            return;
	        }

	        // Set product as request attribute and forward to edit page
	        request.setAttribute("product", product);
	        request.getRequestDispatcher("editproducts.jsp").forward(request, response);

	    } catch (NumberFormatException e) {
	        request.setAttribute("errorMessage", "Invalid product ID format.");
	        request.getRequestDispatcher("productList.jsp").forward(request, response);
	    } catch (Exception e) {
	        // Handle general exceptions (e.g., database errors)
	        request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
	        request.getRequestDispatcher("productList.jsp").forward(request, response);
	    }
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Integer userId = (Integer) session.getAttribute("userId"); // Get user ID from session
	    String userRole = (String) session.getAttribute("userRole"); // Get user role from session

	    if (userId == null || !"seller".equals(userRole)) {
	        // User not logged in or not a seller, redirect to login
	        response.sendRedirect("login.jsp");
	        return;
	    }

	    // Retrieve product information from the request
	    String id = request.getParameter("id");
	    String name = request.getParameter("name");
	    String price = request.getParameter("price");
	    String description = request.getParameter("description");

	    // Validate input
	    if (id == null || id.isEmpty() || name == null || name.isEmpty() || price == null || price.isEmpty() || description == null || description.isEmpty()) {
	        request.setAttribute("errorMessage", "All fields are required.");
	        request.getRequestDispatcher("editproducts.jsp").forward(request, response);
	        return;
	    }

	    // Create a new Product object
	    Product product = new Product();
	    product.setId(Integer.parseInt(id));
	    product.setName(name);
	    product.setPrice(Double.parseDouble(price));
	    product.setDescription(description);

	    ProductDAO productDAO = new ProductDAO();


	    try {
	        // Update the product in the database
	        productDAO.updateProduct(product);

	        // Set response type to HTML
	        response.setContentType("text/html");
	        response.setCharacterEncoding("UTF-8");

	        // Send styled HTML response
	        response.getWriter().write(
	            "<html>" +
	            "<head>" +
	            "<style>" +
	            "body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; padding: 50px; }" +
	            ".message { background-color: #28a745; color: white; padding: 20px; border-radius: 5px; display: inline-block; }" +
	            "</style>" +
	            "</head>" +
	            "<body>" +
	            "<div class='message'>" +
	            "<h2>Product updated successfully!</h2>" +
	            "<a href='productList'>Go back to Product List</a>" +
	            "</div>" +
	            "</body>" +
	            "</html>"
	        );

	    } catch (SQLException e) {
	        e.printStackTrace(); // Log the stack trace for debugging
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("{\"error\":\"Database error while updating product: " + e.getMessage() + "\"}");
	    } catch (Exception e) {
	        e.printStackTrace(); // Catch any other exceptions
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("{\"error\":\"An unexpected error occurred: " + e.getMessage() + "\"}");
	    }
	}

}
