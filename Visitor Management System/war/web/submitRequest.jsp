<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="model.Member" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Submit Visit Request</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <style>
    /* Unified button style: black background, white text */
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
  <h2>Submit Visit Request</h2>
  <%
    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");
    if(error != null){ 
  %>
    <p style="color:red;"><%= error %></p>
  <% 
    } 
    if(message != null){ 
  %>
    <p style="color:green;"><%= message %></p>
  <% 
    } 
  %>
  <form action="SubmitRequestServlet" method="post">
    <label>Visitor Name:</label>
    <input type="text" name="visitorName" required/><br/><br/>
    
    <label>Visitor Password:</label>
    <input type="password" name="visitorPassword" required/><br/><br/>
    
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
    
    <label>Reason for Visit:</label>
    <textarea name="reason" required></textarea><br/><br/>
    
    <label>Date of Visit:</label>
    <input type="date" name="visitDate" required/><br/><br/>
    
    <input type="submit" class="button" value="Submit Request"/>
    <button type="button" class="link-button" onclick="location.href='residentDashboard.jsp';">
      ← Back
    </button>
  </form>
</div>
</body>
</html>
