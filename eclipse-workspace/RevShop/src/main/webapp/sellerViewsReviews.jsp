<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller's Product Reviews</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to an external stylesheet if you have one -->
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #007BFF;
            margin-bottom: 30px;
        }

        form {
            text-align: center;
            margin-bottom: 30px;
        }

        label {
            font-weight: bold;
            margin-right: 10px;
        }

        input[type="text"] {
            padding: 10px;
            width: 200px;
            border: 1px solid #007BFF;
            border-radius: 5px;
        }

        button {
            padding: 10px 15px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        h2 {
            color: #007BFF;
            margin-top: 30px;
            text-align: center;
        }

        ul {
            list-style-type: none;
            padding: 0;
            max-width: 600px;
            margin: 0 auto;
        }

        li {
            background-color: rgba(0, 123, 255, 0.1);
            border: 1px solid #007BFF;
            border-radius: 5px;
            margin: 10px 0;
            padding: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        strong {
            color: #007BFF;
        }

        hr {
            border: 0;
            border-top: 1px solid #007BFF;
            margin: 15px 0;
        }

        @media (max-width: 600px) {
            input[type="text"] {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <h1>View Product Reviews</h1>
    
    <form action="${pageContext.request.contextPath}/sellerViews" method="get">
        <label for="productId">Product ID:</label>
        <input type="text" id="productId" name="productId" required>
        <button type="submit">View Reviews</button>
    </form>
    
    <c:if test="${not empty review}">
        <h2>Reviews for Product <%= request.getAttribute("productId") %></h2>
        <ul>
            <c:forEach var="review" items="${review}">
                <li>
                    <strong>User ID:</strong> ${review.userId} <br>
                    <strong>Rating:</strong> ${review.rating} <br>
                    <strong>Comment:</strong> ${review.comment} <br>
                    <hr>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <a class="back-link" href="sellerDashboard.jsp">Back to Dashboard</a>
</body>
</html>
