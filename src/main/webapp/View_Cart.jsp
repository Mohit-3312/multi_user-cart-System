<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.example.cart.CartItem, com.example.cart.Product" %>
<%
    List<CartItem> cartItems = (List<CartItem>) session.getAttribute("Cart_Items");
    if (cartItems == null) {
        response.sendRedirect("CartServlet");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fff8f0;
            margin: 0;
            padding: 0;
        }

        .navbar {
            background-color: #ff7f00;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-weight: bold;
        }

        .container {
            padding: 20px;
        }

        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
            gap: 20px;
        }

        .product {
            border: 2px solid #ffa500;
            border-radius: 10px;
            padding: 15px;
            background-color: #fff;
            text-align: center;
        }

        .product h3 {
            color: #ff7f00;
        }

        .product button {
            background-color: #ff7f00;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
        }

        .summary {
            margin-top: 20px;
            font-weight: bold;
            color: #333;
            margin-bottom:15px;
        }

        @media (max-width: 600px) {
            .product-grid {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>

    <div class="navbar">
        <div>
            <a href="HomeServlet">Home</a>
            <a href="Logout">Logout</a>
        </div>
        <div>
            Welcome, <%= session.getAttribute("user") %>
        </div>
    </div>
     <%
    String message = (String) session.getAttribute("RemoveMessage");
    if (message != null) {
%>
    <div style="color: green; font-weight: bold;"><%= message %></div>
<%
        session.removeAttribute("RemoveMessage"); // clear after showing
    }
%>

    <div class="container">
        <h2>Your Cart Items</h2>
         <div class="summary">
                Total Items: <%= cartItems.size() %>
            </div>

		<% request.getSession(false);
			if(session == null || session.getAttribute("User_Id") == null )
			{
				%>
				<h2>Can not find user Please Login Again!<a href="Login.jsp">Login</a></h2>
				<% 
			}
			else if (cartItems.isEmpty()) { %>
            <p>Your cart is empty.</p>
        <% } else { %>
            <div class="product-grid">
                <% for (CartItem item : cartItems) {
                    Product p = item.getProduct();
                %>
                    <div class="product">
                        <h3><%= p.getName() %></h3>
                        <p>₹<%= p.getPrice() %></p>
                        <form action="Remove_Cart" method="post">
                            <input type="hidden" name="cartId" value="<%= item.getCartId() %>">
                            <button type="submit">Remove</button>
                        </form>
                    </div>
                <% } %>
            </div>

           
        <% } %>
    </div>

</body>
</html>