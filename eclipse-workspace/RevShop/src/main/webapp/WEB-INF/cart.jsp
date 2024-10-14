<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Shopping Cart</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9; /* Light gray background */
            color: #333; /* Standard text color */
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #2c3e50; /* Dark blue heading */
            margin-bottom: 20px;
            font-size: 2.5em;
            font-weight: bold;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white; /* White background for table */
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
            font-size: 1em;
        }
        th {
            background-color: #3498db; /* Bright blue for table header */
            color: white; /* White text in header */
            text-transform: uppercase;
            font-weight: bold;
        }
        td {
            background-color: #f9f9f9; /* Light gray for table rows */
        }
        td img {
            width: 50px; /* Optional: to display product images */
            height: auto;
        }
        button {
            background-color: #e74c3c; /* Red button */
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #c0392b; /* Darker red on hover */
        }
        form {
            margin: 20px 0;
            text-align: center;
        }
        form input[type="text"], form input[type="number"] {
            padding: 8px;
            margin: 0 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 150px;
        }
        form input[type="submit"], button {
            background-color: #2980b9; /* Blue buttons */
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
        }
        form input[type="submit"]:hover, button:hover {
            background-color: #1c6692; /* Darker blue on hover */
        }
        #total {
            font-weight: bold;
            text-align: center;
            font-size: 24px;
            margin-top: 20px;
            color: #2c3e50; /* Darker blue for total price */
        }
        .error-message {
            color: #e74c3c; /* Red color for error messages */
            text-align: center;
            margin-top: 10px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Your Shopping Cart</h1>

    <c:choose>
        <c:when test="${empty cartItems}">
            <p style="text-align: center; color: #333;">Your cart is empty!</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Remove</th>
                </tr>
                <c:set var="totalPrice" value="0" />
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td>${item.name}</td>
                        <td>$${item.price}</td>
                        <td>${item.quantity}</td>
                        <td>$${item.price * item.quantity}</td>
                        <td>
                            <form action="cart" method="post">
                                <input type="hidden" name="productId" value="${item.id}">
                                <input type="hidden" name="action" value="remove">
                                <button type="submit">Remove</button>
                            </form>
                            
                        </td>
                        
                    </tr>
                    <c:set var="totalPrice" value="${totalPrice + (item.price * item.quantity)}" />
                </c:forEach>
            </table>
            <p id="total">Total Price: $<c:out value="${totalPrice}" /></p>
        </c:otherwise>
    </c:choose>

    <h2 style="text-align: center; color: #2c3e50;">Add Product to Cart</h2>
    <form action="cart" method="post" style="text-align: center;">
        <label for="productId">Product ID:</label>
        <input type="text" id="productId" name="productId" required>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required min="1">
        <input type="hidden" name="action" value="add">
        <input type="submit" value="Add to Cart">
    </form>
    <a class="back-link" href="buyerDashboard.jsp">Back to Dashboard</a>
</body>
</html>