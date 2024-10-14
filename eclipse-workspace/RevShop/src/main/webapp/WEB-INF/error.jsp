<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            background-color: #f0f8ff; /* Light background color */
            font-family: Arial, sans-serif; /* Font style */
            display: flex; /* Use Flexbox to center content */
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            height: 100vh; /* Full viewport height */
            margin: 0; /* Remove default margin */
        }

        .container {
            text-align: center; /* Center text */
            background-color: rgba(255, 255, 255, 0.8); /* Slightly transparent white background */
            padding: 30px; /* Padding around the container */
            border-radius: 10px; /* Rounded corners */
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2); /* Shadow for depth */
        }

        h1 {
            color: #ff4500; /* Vibrant color for the heading */
        }

        p {
            color: #ff6347; /* Vibrant color for error messages */
            font-weight: bold; /* Bold for emphasis */
        }

        input, button {
            padding: 10px; /* Padding for inputs and button */
            margin-top: 10px; /* Space above elements */
            width: 80%; /* Width of input and button */
            max-width: 300px; /* Maximum width */
            border: 1px solid #ccc; /* Border for inputs */
            border-radius: 5px; /* Rounded corners */
        }

        button {
            background-color: #ff6347; /* Vibrant button color */
            color: white; /* Text color for the button */
            cursor: pointer; /* Pointer cursor on hover */
            transition: background-color 0.3s; /* Transition for smooth hover effect */
        }

        button:hover {
            background-color: #ff4500; /* Darker shade on hover */
        }

        a {
            display: inline-block; /* Inline block for better spacing */
            margin-top: 20px; /* Space above the link */
            color: #4682b4; /* Color for the link */
            text-decoration: none; /* Remove underline */
            font-weight: bold; /* Bold link */
        }

        a:hover {
            text-decoration: underline; /* Underline on hover for effect */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Product Details</h1>
        <c:if test="${not empty errorMessage}">
            <p>${errorMessage}</p> <!-- Display the specific error message -->
            <form action="${pageContext.request.contextPath}/productdetails" method="get">
                <label for="productId">Enter Product ID:</label>
                <input type="text" name="productId" id="productId" required>
                <button type="submit">View Product Details</button>
            </form>
        </c:if>
        <a href="buyerDashboard.jsp">Go back</a>
    </div>
</body>
</html>
