<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.example.cart.Product" %>
<%
    List<Product> products = (List<Product>) session.getAttribute("products");
    if (products == null) {
        response.sendRedirect("HomeServlet");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Home Page</title>
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
      grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
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
      <a href="CartServlet">View Cart</a>
    </div>
    <div>
      <%= session.getAttribute("user") != null ? session.getAttribute("user") : "<a href=Login.jsp>Login</a>" %>
    </div>
  </div>
  <%
    String message = (String) session.getAttribute("cartMessage");
    if (message != null) {
%>
    <div style="color: green; font-weight: bold;"><%= message %></div>
<%
        session.removeAttribute("cartMessage"); // clear after showing
    }
%>

  <div class="container">
    <h2>Available Products</h2>
    <div class="product-grid">
<% for (Product p : products) { %>
    <div class="product">
        <h3><%= p.getName() %></h3>
        <p><%= p.getPrice() %></p>
        <form action="Add_Cart" method="post">
            <input type="hidden" name="productId" value="<%= p.getId() %>">
            <input type="hidden" name="productName" value="<%= p.getName() %>">
            <input type="hidden" name="price" value="<%= p.getPrice() %>">
            <button type="submit">Add to Cart</button>
        </form>
    </div>
<% } %>
</div>
  </div>

</body>
</html>