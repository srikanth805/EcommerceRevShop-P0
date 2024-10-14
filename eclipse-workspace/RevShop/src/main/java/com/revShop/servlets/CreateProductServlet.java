package com.revShop.servlets;

import com.revShop.models.Product;
import com.revShop.services.SellerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet("/createProduct")
@MultipartConfig
public class CreateProductServlet extends HttpServlet {
    private SellerService sellerService;

    @Override
    public void init() throws ServletException {
        sellerService = new SellerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/createProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String quantityStr = request.getParameter("quantity");
        Part imagePart = request.getPart("image");

        // Validate input
        if (name == null || name.isEmpty() || priceStr == null || priceStr.isEmpty() || description == null || description.isEmpty() || quantityStr == null|| quantityStr.isEmpty()||imagePart == null) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("/WEB-INF/views/createProduct.jsp").forward(request, response);
            return;
        }

        double price;
        int quantity;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid price format.");
            request.getRequestDispatcher("/WEB-INF/views/createProduct.jsp").forward(request, response);
            return;
        }
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid quantity format.");
            request.getRequestDispatcher("/WEB-INF/views/createProduct.jsp").forward(request, response);
            return;
        }

        // Save image to the server
        String imagePath = saveImage(imagePart);

        // Create and populate the Product object
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setImageUrl(imagePath);

        boolean success;
        try {
            // Updated to remove userId parameter
            success = sellerService.addProduct(product.getName(), product.getPrice(), product.getDescription(),product.getQuantity(),product.getImageUrl());
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding product: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/createProduct.jsp").forward(request, response);
            return;
        }

        if (success) {
            response.sendRedirect("productList");
        } else {
            request.setAttribute("errorMessage", "Product creation failed. It might already exist.");
            request.getRequestDispatcher("/WEB-INF/views/createProduct.jsp").forward(request, response);
        }
    }

    private String saveImage(Part imagePart) throws IOException {
        String imagePath = "images/" + Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        String fullPath = getServletContext().getRealPath("") + imagePath;
        File file = new File(fullPath);
        imagePart.write(fullPath);
        return imagePath;
    }
}
