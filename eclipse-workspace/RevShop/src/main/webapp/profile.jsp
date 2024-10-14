<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: skyblue; /* Sky blue background */
            display: flex;
            justify-content: center; /* Centers the content horizontally */
            align-items: center;     /* Centers the content vertically */
            height: 100vh;           /* Full viewport height */
            margin: 0;
        }

        .profile-box {
            background-color: white; /* White background for the profile box */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }

        .profile-box h2 {
            font-size: 24px;
            color: #333; /* Dark text color */
        }

        .profile-box p {
            font-size: 18px;
            margin: 10px 0;
            color: #555; /* Slightly lighter text color */
        }

        /* Image styling */
        .profile-box img {
            width: 150px; /* Fixed size for the image */
            height: 150px;
            border-radius: 50%; /* Make the image round */
            object-fit: cover; /* Ensures the image covers the entire area */
            margin-bottom: 20px; /* Add some space below the image */
        }
    </style>
</head>
<body>
    <div class="profile-box">
        <!-- User image -->
        <img src="${pageContext.request.contextPath}/images/userimage.png" alt="User Image">
        
        <h2>User Profile</h2>
        <p><strong>User ID:</strong> ${user.id}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Role:</strong> ${user.role}</p>
    </div>
  

</body>
</html>
