<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Record Visitor Details</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <style>
    /* Unified button style */
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
    .container {
      max-width: 500px;
      margin: 20px auto;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Record Visitor Details</h2>
  <%
    String message = (String) request.getAttribute("message");
    if(message != null){ 
  %>
    <p style="color:green;"><%= message %></p>
  <% } %>
  <form action="RecordVisitorServlet" method="post">
    <label>Name:</label>
    <input type="text" name="name" required/><br/><br/>
    
    <label>Password:</label>
    <input type="password" name="password" required/><br/><br/>
    
    <label>Gender:</label>
    <select name="gender">
      <option value="Male">Male</option>
      <option value="Female">Female</option>
    </select><br/><br/>
    
    <label>Phone:</label>
    <input type="text" name="phone" required/><br/><br/>
    
    <label>IC:</label>
    <input type="text" name="ic" required/><br/><br/>
    
    <label>Email:</label>
    <input type="email" name="email" required/><br/><br/>
    
    <label>Address:</label>
    <textarea name="address" required></textarea><br/><br/>
    
    <input type="submit" class="button" value="Save Visitor Details"/>
    <button type="button" class="link-button" onclick="location.href='securityDashboard.jsp';">
      ← Back
    </button>
  </form>
</div>
</body>
</html>
