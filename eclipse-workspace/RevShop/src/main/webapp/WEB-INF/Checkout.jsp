<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-image: url('images/ckout.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            margin: 0;
            padding: 0;
            color: #fff;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .container {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.5);
            width: 80%;
            max-width: 900px;
            margin-top: 50px;
        }

        h1 {
            color: #FFD700;
            text-shadow: 3px 3px 6px rgba(0, 0, 0, 0.7);
            font-size: 3rem;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: rgba(255, 255, 255, 0.1);
            margin: 20px 0;
        }

        th, td {
            border: 1px solid rgba(255, 255, 255, 0.2);
            padding: 15px;
            text-align: center;
            color: #fff;
        }

        th {
            background-color: #4CAF50;
            color: #fff;
            font-size: 1.2rem;
        }

        td {
            font-size: 1rem;
        }

        tr:nth-child(even) {
            background-color: rgba(255, 255, 255, 0.1);
        }

        .total {
            font-size: 1.5rem;
            margin: 20px 0;
            font-weight: bold;
            color: #FFD700;
            text-align: right;
        }

        button {
            background-color: #FFD700;
            color: #333;
            padding: 12px 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1.1rem;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #FFA500;
        }

        a {
            color: #FFD700;
            text-decoration: none;
            font-size: 1.2rem;
            display: inline-block;
            margin-top: 20px;
        }

        a:hover {
            color: #FFA500;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Checkout</h1>
        <c:if test="${not empty cartItems}">
            <table>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
                <c:set var="totalAmount" value="0" />
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td>${item.name}</td>
                        <td>$${item.price}</td>
                        <td>${item.quantity}</td>
                    </tr>
                    <c:set var="totalAmount" value="${totalAmount + (item.price * item.quantity)}" />
                </c:forEach>
            </table>
            <div class="total">
                Total Amount: $${totalAmount}
            </div>
            <form action="checkout" method="post">
                <button type="submit">Place Order</button>
                <a class="back-link" href="buyerDashboard.jsp">Back to Dashboard</a>
            </form>
        </c:if>
        <c:if test="${empty cartItems}">
            <p>Your cart is empty. Please add items to your cart before checking out.</p>
            <a href="productList">Go back to products</a><br>
            <a class="back-link" href="buyerDashboard.jsp">Back to Dashboard</a>
        </c:if>
    </div>
</body>
</html>
