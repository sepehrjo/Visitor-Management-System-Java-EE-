<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Assist Visitor</title>
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
  <h2>Assist Visitor Login</h2>
  <form action="AssistVisitorServlet" method="post">
    <label>Visitor Username:</label>
    <input type="text" name="visitorUsername" required/><br/><br/>
    
    <label>Visitor Password:</label>
    <input type="password" name="visitorPassword" required/><br/><br/>
    
    <input type="submit" class="button" value="Confirm Login" />
  </form>
</body>
</html>
