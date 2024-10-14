<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
       body {
    font-family: Arial, sans-serif;
    background-color: #FFA500; /* Orange background */
    margin: 0; /* Remove default margin */
    padding: 50px 0; /* Top and bottom padding */
    height: 100vh; /* Full viewport height */
}

form {
    width: 90%;
    max-width: 400px;
    margin: auto;
    background: rgba(255, 255, 255, 0.9); /* Semi-transparent white */
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.2);
}

input, button {
    display: block;
    width: calc(100% - 20px);
    padding: 12px;
    margin-bottom: 15px;
    border-radius: 5px;
    border: 1px solid #ccc;
    transition: border 0.3s;
}

input:focus {
    border: 1px solid #FF5733; /* Focus border - bright orange */
}

button {
    background-color: #4CAF50; /* Green background for button */
    color: white;
    border: none;
    cursor: pointer;
    font-weight: bold;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #45a049;
}

h1 {
    font-size: 36px;
    color: #2E8B57; /* Sea green for the heading */
    text-align: center;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

label {
    color: #8B0000; /* Dark red labels */
}

.error-message {
    color: blue;
    font-weight: bold;
}

p {
    font-size: 18px;
    color: blue; /* Dark gray text */
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
}

a {
    color: yellow; /* Orange-red links */
    text-decoration: none;
    font-weight: bold;
}

a:hover {
    text-decoration: underline;
}

/* Additional styling for responsiveness */
@media (max-width: 600px) {
    form {
        padding: 20px;
    }

    input, button {
        padding: 10px;
    }
}

    </style>
</head>
<body>

    <h1>Login</h1>

    <!-- Display error message if present -->
    <c:if test="${not empty errorMessage}">
        <p class="error-message">${errorMessage}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Login</button>
    </form>

    <p>New user? <a href="register.jsp">Register here</a></p>

</body>
</html>
