<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.revShop.dao.OrderDAO" %>
<%@ page import="com.revShop.models.Order" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Orders</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('images/gradient.jpeg');
            background-size: cover;
            color: #333;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            color: #4CAF50;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #e0e0e0;
        }
    </style>
</head>
<body>
    <h1>Your Orders</h1>
    
    <c:set var="orderDAO" value="${new com.revShop.dao.OrderDAO()}" />
    <c:set var="orders" value="${orderDAO.getAllOrders()}" />

    <table>
        <tr>
            <th>Order ID</th>
            <th>User ID</th>
            <th>Order Date</th>
            <th>Total Amount</th>
            <th>Action</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.userId}</td>
                <td>${order.orderDate}</td>
                <td>$<c:out value="${order.totalAmount}" /></td>
                <td><a href="orderDetails.jsp?id=${order.id}">View Details</a></td>
            </tr>
        </c:forEach>
    </table>

    <a href="sellerDashboard.jsp">Back to Dashboard</a>
</body>
</html>
