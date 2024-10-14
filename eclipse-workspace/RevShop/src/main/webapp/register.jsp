<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Registration</title>
    <style>
        body {
            background-image: url('images/regi.png');
            background-size: cover; /* Ensures the image covers the entire page */
            background-repeat: no-repeat; /* Prevents the image from repeating */
            font-family: Arial, sans-serif;
            margin: 0; /* Remove default margin */
            padding: 0; /* Remove default padding */
            height: 100vh; /* Full height for the body */
        }

        .register-container {
            width: 350px;
            padding: 30px;
            margin: auto;
            background-color: rgba(255, 255, 255, 0.9); /* Slightly transparent white background */
            border-radius: 8px;
            text-align: center;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3); /* Add some shadow for depth */
            position: relative; /* For absolute positioning of the content */
            top: 50%; /* Center vertically */
            transform: translateY(-50%); /* Adjust for exact centering */
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc; /* Light gray border */
            border-radius: 4px; /* Rounded corners */
            transition: border 0.3s; /* Smooth transition for border */
        }

        input:focus, select:focus {
            border: 1px solid #4CAF50; /* Green border on focus */
            outline: none; /* Remove default outline */
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            border-radius: 4px; /* Rounded corners for the button */
            transition: background-color 0.3s; /* Smooth transition for background color */
        }

        button:hover {
            background-color: #45a049; /* Darker green on hover */
        }

        h1 {
            color: #4CAF50; /* Title color */
            margin-bottom: 20px; /* Space below the title */
        }

        .footer {
            position: absolute; /* Absolute positioning for the footer */
            bottom: 20px; /* Distance from the bottom */
            width: 100%;
            text-align: center;
            color: #555; /* Dark gray color for footer */
        }
        p{
        color:red;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h1>User Registration</h1>
        <c:if test="${not empty errorMessage}">
        <p class="error-message">${errorMessage}</p>
        </c:if>
        <form action="register" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
            <select name="role" required>
                <option value="" disabled selected>Select Role</option>
                <option value="buyer">Buyer</option>
                <option value="seller">Seller</option>
            </select>
            <button type="submit">Register</button>
        </form>
        <div class="footer">
            Already have an account? <a href="login.jsp">Login here</a>
        </div>
    </div>
</body>
</html>
