<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <style>
        body {
            background-image: url('${pageContext.request.contextPath}/images/gradient.jpeg');
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* Header with Banner */
        .header-banner {
            background-image: url('${pageContext.request.contextPath}/images/header.jpg');
            background-size: cover;
            background-position: center;
            height: 300px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7);
            position: relative;
            overflow: hidden;
        }

        .header-banner h1 {
            font-size: 40px;
            margin: 0;
            white-space: nowrap; /* Keep the text on one line */
            position: absolute;
            animation: scrollText 10s linear infinite; 
            color:orange;/* Animation for rolling effect */
        }

        /* Rolling Text Animation */
        @keyframes scrollText {
            0% {
                transform: translateX(100%); /* Start off-screen to the right */
            }
            100% {
                transform: translateX(-100%); /* End off-screen to the left */
            }
        }

        /* Page Container */
        .content {
            padding: 20px;
        }

        /* Updated Product Grid Layout for 4 grids in a row */
        .product-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr); /* 4 grids per row */
            gap: 20px; /* Gap between each grid */
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        /* Styling for Odd and Even Grids */
        .product-card:nth-child(odd) {
            background-color: skyblue; /* Background for odd grids */
        }

        .product-card:nth-child(even) {
            background-color: orange; /* Background for even grids */
        }

        /* Product Card */
        .product-card {
            border-radius: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 15px;
            text-align: center;
            transition: background-color 0.3s ease;
        }

        /* Rounded Corners for Images */
        .product-card img {
            width: 100%;
            object-fit: cover;
            height: 200px;
            border-radius: 20px;
        }

        .product-card h3 {
            font-size: 18px;
            margin: 10px 0;
        }

        .product-card p {
            font-size: 14px;
            color: #777;
        }

        .product-card .price {
            font-size: 16px;
            color: #333;
            margin: 10px 0;
        }

        .product-card a {
            display: block;
            text-decoration: none;
            color: #007bff;
            margin-top: 10px;
        }

        .product-card a:hover {
            color: #0056b3;
        }

        /* Button to Add Product */
        .add-product {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .add-product:hover {
            background-color: #0056b3;
        }

        /* Search Input and Button */
        .search-container {
            text-align: center;
            margin-bottom: 30px;
        }

        .search-input {
            padding: 10px;
            width: 300px;
            font-size: 16px;
            border: 2px solid #007bff;
            border-radius: 5px;
            outline: none;
            transition: border-color 0.3s ease;
        }

        .search-button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            margin-left: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .search-input:focus {
            border-color: #0056b3;
        }

        .search-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <!-- Header with Banner -->
    <div class="header-banner">
        <h1>Welcome to Our Product Store</h1>
    </div>

    <!-- Page Content -->
    <div class="content">

        <!-- Search Container -->
        <div class="search-container">
            <form method="get" action="${pageContext.request.contextPath}/productList">
                <input type="text" name="search" placeholder="Search products..." class="search-input" value="${param.search}" />
                <button type="submit" class="search-button">Search</button>
            </form>
        </div>

        <!-- Display User Role -->
        <p style="display: inline-block; padding: 8px 15px; font-size: 14px; color: #fff; background-color: #007bff; border-radius: 20px; font-weight: bold; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); margin-bottom: 20px; text-align: center;">
            User Role: ${sessionScope.userRole}
        </p>

        <!-- Error Message -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                <p>${errorMessage}</p>
            </div>
        </c:if>

        <!-- Add Product for Sellers -->
        <c:if test="${sessionScope.userRole eq 'seller'}">
            <a href="${pageContext.request.contextPath}/createProduct" class="add-product">Add New Product</a>
        </c:if>

        <!-- Product Grid Display -->
        <div class="product-grid">
            <c:forEach var="product" items="${products}">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/${product.imageUrl}" alt="Image of ${product.name}" />
                    <h3>${product.name}</h3>
                    <p>${product.description}</p>
                    <p class="price">$${product.price}</p>
                    <a href="${pageContext.request.contextPath}/productdetails?productId=${product.id}">View</a>

                    <c:if test="${sessionScope.userRole eq 'seller'}">
                        <a href="${pageContext.request.contextPath}/editproducts?id=${product.id}">Edit</a>
                        <a href="${pageContext.request.contextPath}/deleteProduct?id=${product.id}" onclick="return confirm('Are you sure you want to delete this product?');">Delete</a>
                        <a class="back-link" href="sellerDashboard.jsp">Back to Dashboard</a>
                    </c:if>
                </div>
            </c:forEach>

            <!-- If No Products Available -->
            <c:if test="${empty products}">
                <p>No products available.</p>
            </c:if>
        </div>
    </div>

</body>
</html>
