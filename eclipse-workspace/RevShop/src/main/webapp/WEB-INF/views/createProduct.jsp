<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Product</title>
   <style>
    body {
        background-image: url('images/crt.jpg'); /* Update path as needed */
        background-size: cover; /* Cover the entire background */
        background-position: center; /* Center the background image */
        background-repeat: no-repeat; /* Prevent background repetition */
        color: white; /* Text color */
        font-family: Arial, sans-serif; /* Font style */
        display: flex;
        flex-direction: column; /* Stack elements vertically */
        justify-content: center; /* Center vertically */
        align-items: center; /* Center horizontally */
        height: 100vh; /* Full viewport height */
        margin: 0; /* Remove default margin */
    }

    h1 {
        margin-bottom: 20px; /* Spacing below title */
        text-align: center; /* Center title */
        font-size: 2.5em; /* Increase font size */
        font-weight: bold; /* Bold font for title */
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7); /* Shadow for text */
    }

    form {
        background: rgba(0, 0, 0, 0.8); /* Darker semi-transparent background for form */
        padding: 30px; /* Increased padding for a better layout */
        border-radius: 10px; /* More rounded corners */
        box-shadow: 0 8px 30px rgba(0, 0, 0, 0.6); /* More prominent shadow effect */
        width: 350px; /* Set form width */
        display: flex; /* Use flexbox for form layout */
        flex-direction: column; /* Stack elements vertically in the form */
        transition: transform 0.3s ease; /* Smooth transition for form hover */
    }

    form:hover {
        transform: scale(1.05); /* Slightly enlarge form on hover */
    }

    label {
        display: block; /* Block display for labels */
        font-weight: bold; /* Bold text for labels */
        margin: 10px 0 5px; /* Spacing */
    }

    input[type="text"],
    input[type="number"],
    textarea,
    input[type="file"] {
        width: 100%; /* Full width inputs */
        padding: 12px; /* Increased padding inside input fields */
        border: 1px solid #ccc; /* Border style */
        border-radius: 6px; /* More rounded corners */
        box-sizing: border-box; /* Include padding and border in width */
        margin-bottom: 15px; /* Spacing below inputs */
        transition: border 0.3s; /* Smooth transition for border */
    }

    input[type="text"]:focus,
    input[type="number"]:focus,
    textarea:focus,
    input[type="file"]:focus {
        border-color: #28a745; /* Change border color on focus */
        outline: none; /* Remove default outline */
    }

    button {
        background-color: #28a745; /* Green button */
        color: white; /* White text */
        border: none; /* No border */
        padding: 12px; /* Button padding */
        border-radius: 6px; /* More rounded corners */
        cursor: pointer; /* Pointer cursor */
        width: 100%; /* Full width button */
        font-size: 1.1em; /* Increase button font size */
        transition: background-color 0.3s; /* Smooth transition for background color */
    }

    button:hover {
        background-color: #218838; /* Darker green on hover */
    }

    .error-message {
        color: red; /* Error message color */
        margin: 10px 0; /* Spacing */
        text-align: center; /* Center error message */
    }

    .back-link {
        display: inline-block; /* Block display */
        text-align: center; /* Center link */
        margin-top: 20px;
        margin-right:10px; /* Spacing above link */
        color: white; /* Link color */
        text-decoration: none; /* No underline */
        font-size: 1em; /* Increase font size */
        font-weight: bold; /* Bold font for link */
        transition: color 0.3s; /* Smooth transition for link color */
    }

    .back-link:hover {
        color: #28a745; /* Change color on hover */
        text-decoration: underline; /* Underline on hover */
    }
</style>

</head>
<body>
    <h1>Create Product</h1>

    <c:if test="${not empty errorMessage}">
        <p class="error-message">${errorMessage}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/createProduct" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" placeholder="Enter product name" required>
        </div>
        <div>
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" placeholder="Enter price" required>
        </div>
        <div>
            <label for="description">Description:</label>
            <textarea id="description" name="description" placeholder="Enter product description" required></textarea>
        </div>
        <div>
            <label for="quantity">Quantity:</label>
             <input type="number" id="quantity" name="quantity" placeholder="Enter quantity" required>
        </div>
        
        <div>
            <label for="image">Product Image:</label>
            <input type="file" id="image" name="image" accept="image/*" required>
        </div>
       
        <div>
            <button type="submit">Add Product</button>
        </div>
    </form>

    <a class="back-link" href="productList">Back to Product List</a>
     <a class="back-link" href="sellerDashboard.jsp">Back to Dashboard</a>
</body>
</html>
