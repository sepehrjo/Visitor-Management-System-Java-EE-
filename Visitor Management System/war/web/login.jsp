<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <style>
    .link-button, .button {
      display: inline-block;
      padding: 10px 20px;
      margin: 10px 5px;
      background-color: black;
      color: white;
      border: none;
      font-size: 14px;
      cursor: pointer;
    }
    .link-button:hover, .button:hover {
      background-color: #333;
    }
  </style>
</head>
<body>
  <h2>Login</h2>
  <% 
    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");
    if(error != null){ %>
      <p style="color:red;"><%= error %></p>
  <% } 
    if(message != null){ %>
      <p style="color:green;"><%= message %></p>
  <% } %>
  <form action="LoginServlet" method="post">
    <label>Username:</label>
    <input type="text" name="username" required /><br/><br/>
    <label>Password (numeric):</label>
    <input type="text" name="password" required /><br/><br/>
    <input type="submit" class="button" value="Login" />
  </form>
  <br/>
  <button type="button" class="link-button" onclick="location.href='register.jsp';">
    Register
  </button>
</body>
</html>
