<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('images/gradient.jpeg'); /* Add your background image here */
            background-size: cover;
            color: #333;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            color: #4CAF50; /* Main heading color */
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.6); /* Add depth to the heading */
            margin-bottom: 20px;
        }
        .message {
            text-align: center;
            margin: 20px;
            font-size: 1.5em; /* Increased font size for better readability */
            padding: 20px;
            border-radius: 10px; /* Rounded corners */
            width: 80%; /* Added width for better layout */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            background-color: rgba(255, 255, 255, 0.9); /* Slightly transparent background */
        }
        .success {
            color: #4CAF50; /* Green color for success messages */
        }
        .error {
            color: #D32F2F; /* Red color for error messages */
        }
        .back-link {
            text-align: center;
            margin-top: 20px;
            font-size: 1.2em; /* Slightly larger font for the link */
        }
        a {
            color: #4CAF50; /* Link color */
            text-decoration: none; /* Remove underline */
            transition: color 0.3s; /* Transition effect for hover */
        }
        a:hover {
            color: #45a049; /* Darker shade on hover */
        }
    </style>
</head>
<body>
    <h1>Checkout Result</h1>
    <div class="message">
        <c:choose>
            <c:when test="${not empty message}">
                <p class="success"><c:out value="${message}" /></p>
            </c:when>
            <c:otherwise>
                <p class="error">An error occurred during checkout. Please try again.</p>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="back-link">
        <a href="cart">Back to Cart</a>
    </div>
</body>
</html>
