<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"> 
    <style>
        body { 
            background-color: #f0f8ff; /* Light blue background color */
            font-family: Arial, sans-serif; 
            color: #333; 
            margin: 0;
            padding: 0;
        }
        
        /* Styling the header section */
        .header {
            background-color: rgba(0, 123, 255, 0.9);
            padding: 10px;
            text-align: center;
        }

        .header img {
            max-width: 100px;
            display: block;
            margin: 0 auto 10px;
        }

        .header h1 {
            color: yellow;
            margin: 10px 0;
        }

        /* Creating navigation bar */
        .nav-tabs {
            display: flex;
            justify-content: space-around;
            background-color: #007bff;
            padding: 15px 0;
            border-radius: 8px;
        }

        .nav-tabs a {
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s, color 0.3s;
        }

        .nav-tabs a:hover {
            background-color: #0056b3;
            color: #fff;
        }

        /* Active tab styling */
        .nav-tabs a.active {
            background-color: #0056b3;
            border-radius: 5px;
        }

        /* Main container */
        .container { 
            padding: 40px; 
            max-width: 800px; 
            margin: 50px auto; 
            background-color: rgba(255, 255, 255, 0.9); 
            border-radius: 8px; 
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* Adding shadow for depth */
            border: 2px solid #007bff; /* Blue border */
        }

        /* Content styling */
        .container p {
            font-size: 18px; /* Larger font size */
            line-height: 1.6; /* Improved line height for readability */
            color: #333; /* Darker text color for contrast */
            background-color: #e9f7fe; /* Light blue background for the paragraph */
            padding: 20px; /* Padding inside the paragraph */
            border-radius: 5px; /* Rounded corners */
            border-left: 5px solid #007bff; /* Left border for accent */
        }

        /* Responsive styling */
        @media (max-width: 600px) {
            .nav-tabs a {
                font-size: 14px;
                padding: 10px;
            }

            .header h1 {
                font-size: 20px;
            }

            .container {
                padding: 20px;
                margin-top: 20px;
            }
        }
    </style>
</head>
<body>

<!-- Navigation Tabs -->
<div class="nav-tabs">
    <a href="${pageContext.request.contextPath}/profile" aria-label="View Profile"><i class="fas fa-user"></i> Profile</a>
    <a href="${pageContext.request.contextPath}/createProduct" aria-label="Add New Product"><i class="fas fa-plus"></i> Product Management</a>
    <a href="${pageContext.request.contextPath}/orders" aria-label="View Orders"><i class="fas fa-receipt"></i> View Orders</a>
    <a href="${pageContext.request.contextPath}/sellerViewsReviews.jsp" aria-label="View Reviews"><i class="fas fa-star"></i> View Reviews</a>
    <a href="logout.jsp" aria-label="Logout" onclick="return confirm('Are you sure you want to logout?');"><i class="fas fa-sign-out-alt"></i> Logout</a>
</div>

<!-- Seller Logo -->
<div class="header">
    <img src="images/sellerlogo.jpeg" alt="Your Logo"/>
    <h1>Seller DashBoard</h1>
    <h1>Welcome, ${sessionScope.username}</h1> <!-- Displaying the user's email -->
</div>

<!-- Content Section -->
<div class="container">
    <p>Select an option from the tabs above to manage your products and view information.</p>
</div>
<a class="back-link" href="login.jsp">Back to login</a>

</body>
</html>
