<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.revShop.models.Product" %>
<%@ page import="com.revShop.models.Cart" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Order</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file if needed -->
</head>
<body>
    <h1>Confirm Your Order</h1>

    <%
        // Retrieve products in cart and cart object
        List<Product> productsInCart = (List<Product>) request.getAttribute("productsInCart");
        Cart cart = (Cart) request.getAttribute("cart"); // Ensure you set the Cart object in ConfirmOrderServlet
        double totalPrice = 0;

        // Check if cart is not null and productsInCart is not empty
        if (cart != null && productsInCart != null && !productsInCart.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (Product product : productsInCart) {
                    // Get quantity from cart items
                    int quantity = cart.getItems().get(product.getId());
                    double productTotalPrice = product.getPrice() * quantity;
                    totalPrice += productTotalPrice;
            %>
                    <tr>
                        <td><%= product.getName() %></td>
                        <td>$<%= String.format("%.2f", product.getPrice()) %></td>
                        <td><%= quantity %></td>
                        <td>$<%= String.format("%.2f", productTotalPrice) %></td>
                    </tr>
            <%
                }
            %>
                <tr>
                    <td colspan="3" style="text-align: right;"><strong>Total Price:</strong></td>
                    <td>$<%= String.format("%.2f", totalPrice) %></td>
                </tr>
            </tbody>
        </table>

        <form action="placeOrder" method="post">
            <input type="hidden" name="userId" value="<%= cart.getUserId() %>"> <!-- Use getUserId from Cart -->
            <button type="submit">Confirm Order</button>
        </form>

        <a href="viewCart.jsp">Edit Cart</a> <!-- Link to edit cart if necessary -->
    <%
        } else {
    %>
        <p>No products in the cart. Please add items to your cart before confirming an order.</p>
    <%
        }
    %>
</body>
</html>
