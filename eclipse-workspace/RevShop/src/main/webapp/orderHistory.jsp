<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Order History</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-image: url('images/hstry.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            margin: 0;
            padding: 20px;
            color: #444;
            display: flex;
            flex-direction: column;
            align-items: center; /* Center the content */
        }

        h1 {
            text-align: center;
            color: #fff;
            font-size: 36px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            max-width: 800px; /* Set a maximum width */
            margin: 20px 0;
            border-collapse: collapse;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
            background-color: rgba(255, 255, 255, 0.9); /* Slightly more opaque */
            border-radius: 12px;
            overflow: hidden;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        table:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        }

        th, td {
            padding: 20px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            font-size: 16px;
            color: #333;
        }

        th {
            background-color: #007BFF;
            color: white;
            text-transform: uppercase;
            font-size: 14px;
        }

        tr:hover {
            background-color: rgba(0, 123, 255, 0.1);
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
            background-color: rgba(0, 123, 255, 0.1);
            border: 1px solid #bee3f8;
            border-radius: 6px;
            margin: 5px 0;
            padding: 10px;
            color: #31708f;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        li:hover {
            background-color: #007BFF;
            color: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        .no-orders {
            text-align: center;
            color: #d9534f;
            font-weight: bold;
            padding: 30px;
            font-size: 18px;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            margin-top: 30px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
            max-width: 800px; /* Set a maximum width for no orders message */
        }

        /* Button styling */
        a {
            display: inline-block;
            text-decoration: none;
            padding: 10px 20px;
            background-color: #28a745;
            color: #fff;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        a:hover {
            background-color: #218838;
            transform: translateY(-2px);
        }

        /* Responsive adjustments */
        @media (max-width: 600px) {
            th, td {
                padding: 15px;
                font-size: 14px;
            }

            h1 {
                font-size: 28px;
            }

            .no-orders {
                font-size: 16px;
            }
        }
    </style>
</head>
<body>
    <h1>Your Order History</h1>
    <c:if test="${not empty orders}">
        <table>
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Total Amount</th>
                <th>Products</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.totalAmount}</td>
                    <td>
                        <ul>
                            <c:forEach var="product" items="${order.products}">
                                <li>${product.name} (Qty: ${product.quantity})</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a class="back-link" href="buyerDashboard.jsp">Back to Dashboard</a>
    </c:if>
    <c:if test="${empty orders}">
        <p class="no-orders">You have no orders in your history.</p>
        <a class="back-link" href="buyerDashboard.jsp">Back to Dashboard</a>
    </c:if>
</body>
</html>
