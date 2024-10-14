<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
    <title>Reviews for Product <%= request.getAttribute("productId") %></title>
</head>
<body>
    <h1>Reviews for Product <%= request.getAttribute("productId") %></h1>

    <!-- Display Existing Reviews -->
    <ul>
        <c:forEach var="review" items="${reviews}">
            <li>
                <strong>User ID:</strong> ${review.userId} <br>
                <strong>Rating:</strong> ${review.rating} <br>
                <strong>Comment:</strong> ${review.comment} <br>
                <hr>
            </li>
        </c:forEach>
    </ul>

    <!-- Add Review Form -->
    <h2>Add Your Review</h2>
    <form action="AddReviewServlet" method="post">
        <input type="hidden" name="productId" value="<%= request.getAttribute("productId") %>">
        
        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required><br>

        <label for="rating">Rating:</label>
        <input type="number" id="rating" name="rating" min="1" max="5" required><br>

        <label for="comment">Comment:</label>
        <textarea id="comment" name="comment" required></textarea><br>

        <button type="submit">Submit Review</button>
    </form>
</body>
</html>
