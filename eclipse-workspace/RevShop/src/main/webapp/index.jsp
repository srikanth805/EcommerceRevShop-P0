<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>RevShop - Welcome</title>
    <meta name="description" content="RevShop, your one-stop e-commerce platform for buyers and sellers">
    <meta name="keywords" content="e-commerce, online shopping, buy, sell, RevShop">
    <meta name="author" content="RevShop Team">
    <style>
    html, body {
    height: 100%;
    margin: 0; /* Remove default margin */
    padding: 0; /* Remove default padding */
}

body { 
    background-image: url('images/gradient.jpeg'); 
    background-size: cover; /* This ensures the image covers the entire page */
    background-repeat: no-repeat; /* Prevents the image from repeating */
    background-position: center; /* Positions the image at the center of the page */
    font-family: Arial, sans-serif; 
    text-align: center; 
    display: flex; /* Use flexbox for centering */
    justify-content: flex-start; /* Align to the left */
    align-items: center; /* Center vertically */
    padding-left: 2in; /* Move everything 2 inches to the left */
}

.container { 
    padding: 50px; 
    background-color: rgba(255, 255, 255, 0.8); /* Optional: add a background for better readability */
    border-radius: 10px; /* Optional: round corners */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* Optional: add shadow */
}

.button {
    padding: 10px 20px; /* Keep existing padding */
    background-color: #4CAF50; /* Button background color */
    color: #ffffff; /* Text color (white) */
    border: 2px solid #FF5733; /* Add a vibrant border color */
    border-radius: 5px; /* Add some border radius for a rounded effect */
    cursor: pointer; /* Cursor changes to pointer on hover */
    font-family: 'Arial', sans-serif; /* Font family */
    font-size: 1.2em; /* Font size */
    text-align: center; /* Center text */
    width: 200px; /* Set a fixed width to make buttons the same size */
}

/* Optional: Add hover effect */
.button:hover {
    background-color: #45a049; /* Slightly darker background on hover */
    color: #ffffff; /* Keep text color white on hover */
    border-color: #FFC300; /* Change border color on hover */
}

.vibrant-header {
    font-family: 'Arial', sans-serif; /* You can choose any font you prefer */
    font-size: 2em; /* Adjust the size as needed */
    font-weight: bold; /* Make the text bold */
    color: #FF5733; /* Vibrant color (e.g., a bright coral) */
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* Adds a subtle shadow for depth */
    text-align: center; /* Centers the text */
    margin: 20px 0; /* Adds some space around the header */
}
    
    </style>


</head>
<body>
    <div class="container">
        <h1>Welcome to RevShop</h1>
        <c:choose>
            <c:when test="${not empty sessionScope.loggedInUser}">
                <!-- User is logged in, show a personalized message -->
                <h2>Welcome back, ${sessionScope.loggedInUser}!</h2>
                <p>You are logged in as <strong>${sessionScope.userRole}</strong></p>

                <c:choose>
                    <c:when test="${sessionScope.userRole == 'buyer'}">
                        <button onclick="window.location.href='productList.jsp'" class="button">View Products</button>
                        <button onclick="window.location.href='cart.jsp'" class="button">View Cart</button>
                        <button onclick="window.location.href='orderHistory.jsp'" class="button">Order History</button>
                    </c:when>
                    <c:when test="${sessionScope.userRole == 'seller'}">
                        <button onclick="window.location.href='createProduct.jsp'" class="button">Add Product</button>
                        <button onclick="window.location.href='sellerDashboard.jsp'" class="button">Manage Inventory</button>
                        <button onclick="window.location.href='orders.jsp'" class="button">View Orders</button>
                    </c:when>
                </c:choose>
                <button onclick="window.location.href='logout.jsp'" class="button">Logout</button>
            </c:when>
            <c:otherwise>
                <!-- User is not logged in, show login and registration buttons -->
                <h2 class="vibrant-header">Please log in or register:</h2>

                <button onclick="window.location.href='login.jsp'" class="button">Login</button>
                <button onclick="window.location.href='register.jsp'" class="button">Register</button>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
