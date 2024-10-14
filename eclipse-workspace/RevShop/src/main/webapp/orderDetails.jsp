<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Light background for better contrast */
            color: #333;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #007bff; /* Primary color for the header */
            margin-bottom: 20px;
            font-size: 2.5em; /* Increased font size */
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Subtle shadow around the table */
            border-radius: 8px; /* Rounded corners */
            overflow: hidden; /* Prevents child elements from being rounded */
        }

        th, td {
            padding: 12px;
            border: 1px solid #dee2e6; /* Slightly darker border for contrast */
            text-align: left;
        }

        th {
            background-color: #007bff; /* Primary color for header */
            color: white; /* White text on header */
            font-weight: bold; /* Bold text */
        }

        tr:nth-child(even) {
            background-color: #f2f2f2; /* Light grey for even rows */
        }

        tr:hover {
            background-color: #e9ecef; /* Light hover effect */
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            body {
                padding: 10px; /* Reduced padding on smaller screens */
            }

            h1 {
                font-size: 2em; /* Smaller header on small screens */
            }

            table {
                font-size: 14px; /* Smaller font size in table */
            }
        }
    </style>
</head>
<body>
    <h1>Order Details</h1>
    <table>
        <tr>
            <th>Order ID</th>
            <th>User ID</th>
            <th>Order Date</th>
            <th>Total Amount</th>
        </tr>
        <c:forEach var="order" items="${orderList}">
            <tr>
                <td>${order.id}</td>
                <td>${order.userId}</td>
                <td>${order.orderDate}</td>
                <td>${order.totalAmount}</td>
            </tr>
        </c:forEach>
    </table>
    <a class="back-link" href="sellerDashboard.jsp">Back to Dashboard</a>
</body>
</html>
