<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Welcome to Our System</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <style>
    /* Unified button style: black background with white text */
    .link-button {
      display: inline-block;
      padding: 10px 20px;
      margin: 10px 5px;
      background-color: black;
      color: white;
      border: none;
      font-size: 14px;
      cursor: pointer;
    }
    .link-button:hover {
      background-color: #333;
    }
  </style>
</head>
<body>
  <h1>Welcome to the Enterprise System</h1>
  <p>Please choose an option:</p>
  <button type="button" class="link-button" onclick="location.href='login.jsp';">Login</button><br/><br/>
  <button type="button" class="link-button" onclick="location.href='register.jsp';">Register</button>
</body>
</html>
