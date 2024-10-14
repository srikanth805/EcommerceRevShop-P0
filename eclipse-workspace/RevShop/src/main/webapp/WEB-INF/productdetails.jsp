<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('${pageContext.request.contextPath}/images/gradient.jpeg'); /* Updated path with context */
            background-size: cover;
            background-repeat: no-repeat;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        .product-details, .reviews, .review-form {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
            max-width: 800px;
            margin-left: auto;
            margin-right: auto;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="number"], textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: green;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: darkgreen;
        }
        
        .back-link a{
        color:orange;
        border:2px solid red;
        padding:5px 10px;
        border-radius:5px;
        transition:background-color 0.3s;
        }
    </style>
</head>
<body>
    <c:choose>
        <c:when test="${not empty product}">
            <div class="product-details">
                <h1>${product.name}</h1>
                <p><strong>Price:</strong> $${product.price}</p>
                <p><strong>Description:</strong> ${product.description}</p>
                <c:if test="${not empty product.imageUrl}">
                      <img src="${pageContext.request.contextPath}/${product.imageUrl}" alt="${product.name}" width="200"/>
                   </c:if>
            </div>

            <div class="reviews">
                <h2>Reviews</h2>
                <c:if test="${not empty reviews}">
                    <ul>
                        <c:forEach var="review" items="${reviews}">
                            <li>
                                <strong>Rating:</strong> ${review.rating}/5<br>
                                <p>${review.comment}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${empty reviews}">
                    <p>No reviews yet.</p>
                </c:if>
            </div>

            <div class="review-form">
                <h2>Add a Review</h2>
                <form action="${pageContext.request.contextPath}/reviewsss" method="post">
                    <label for="rating">Rating (1-5):</label>
                    <input type="number" id="rating" name="rating" min="1" max="5" required>

                    <label for="comment">Comment:</label>
                    <textarea id="comment" name="comment" rows="4" required></textarea>

                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="submit" value="Submit Review">
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div class="product-details">
                <h2>Product not found.</h2>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="back-link">
        <a href="${pageContext.request.contextPath}/productList">Back to Product List</a>
        <a href="${pageContext.request.contextPath}/buyerDashboard.jsp">Back to DashBoard</a>
    </div>
</body>
</html>
