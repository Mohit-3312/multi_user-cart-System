<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <style>
    body {
      background-color: #fff8f0;
      font-family: Arial, sans-serif;
    }
    .container {
      width: 300px;
      margin: 100px auto;
      padding: 20px;
      border: 2px solid #ffa500;
      border-radius: 10px;
      background-color: #fff;
    }
    h2 {
      text-align: center;
      color: #ff7f00;
    }
    input[type="text"], input[type="password"] {
      width:90%;
      padding: 10px;
      margin: 8px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    input[type="submit"] {
      width: 90%;
      background-color: #ff7f00;
      color: white;
      padding: 10px;
      border: none;
      margin-bottom:10px;
      border-radius: 5px;
      cursor: pointer;
    }
    input[type="submit"]:hover {
      background-color: #e67300;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Login</h2>
    <form action="Validate_User" method="post">
      <input type="text" name="username" placeholder="Username" required>
      <input type="password" name="password" placeholder="Password" required>
      <input type="submit" value="Login">
      Don't Have An Account? <a href="Registration.jsp">Register Now</a>
    </form>
  </div>
</body>
</html>