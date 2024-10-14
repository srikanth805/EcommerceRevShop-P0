<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.revShop.models.Product" %>
<%
    Product product = (Product) request.getAttribute("product"); // Retrieve product from request
    String errorMessage = (String) request.getAttribute("errorMessage"); // Retrieve error message if any
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-image: url('images/gradient.jpeg'); /* Add your background image here */
            background-size: cover; /* Cover the entire background */
            color: #333; /* Text color */
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #fff; /* Make the heading white for better visibility */
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7); /* Add shadow for depth */
            font-size: 2.5em; /* Increase font size */
            margin-bottom: 20px; /* Add margin for spacing */
        }
        .container {
            max-width: 600px;
            margin: auto;
            background: rgba(255, 255, 255, 0.9); /* Slightly transparent white */
            padding: 30px; /* Increase padding for spaciousness */
            border-radius: 12px; /* Round corners */
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.5); /* Stronger shadow */
        }
        label {
            display: block;
            margin: 15px 0 5px;
            font-weight: bold; /* Bold labels */
            color: #007BFF; /* Blue label color */
        }
        input[type="text"], input[type="number"], textarea {
            width: calc(100% - 20px); /* Full width minus padding */
            padding: 12px; /* Increase padding */
            border: 1px solid #007BFF; /* Blue border */
            border-radius: 5px; /* Round input corners */
            margin-bottom: 20px; /* Increase margin */
            font-size: 16px; /* Increase font size */
        }
        input[type="submit"] {
            background-color: #28a745; /* Green button color */
            color: #fff;
            border: none;
            padding: 12px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px; /* Increase font size */
            transition: background-color 0.3s, transform 0.2s; /* Add transition */
            width: 100%; /* Full width button */
        }
        input[type="submit"]:hover {
            background-color: #218838; /* Darker green on hover */
            transform: scale(1.05); /* Slightly enlarge on hover */
        }
        .error-message {
            color: red;
            margin-bottom: 20px;
            text-align: center;
            font-weight: bold; /* Bold error message */
        }
        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #007BFF;
            text-align: center;
            font-weight: bold; /* Bold link */
            transition: color 0.3s; /* Transition effect */
        }
        a:hover {
            text-decoration: underline; /* Underline on hover */
            color: #0056b3; /* Darker blue on hover */
        }
        textarea {
            height: 100px; /* Set height for textarea */
            resize: none; /* Prevent resizing */
        }
        @media (max-width: 600px) {
            .container {
                width: 90%; /* Responsive width */
            }
        }
    </style>
</head>
<body>
    <h1>Edit Product</h1>

    <div class="container">
        <!-- Display error message if exists -->
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
        
        <!-- Ensure product is not null before displaying the form -->
        <% if (product != null) { %>
            <form action="editproducts" method="post">
                <input type="hidden" name="id" value="<%= product.getId() %>"> <!-- Hidden field for product ID -->
                
                <label for="name">Product Name:</label>
                <input type="text" name="name" value="<%= product.getName() %>" required>

                <label for="price">Price:</label>
                <input type="number" name="price" value="<%= product.getPrice() %>" step="0.01" required>

                <label for="description">Description:</label>
                <textarea name="description" required><%= product.getDescription() %></textarea>

                <input type="submit" value="Update Product">
            </form>
        <% } else { %>
            <div class="error-message">Product not found.</div>
        <% } %>

        <a href="productList">Cancel</a> <!-- Link to return to product list -->
    </div>
</body>
</html>
