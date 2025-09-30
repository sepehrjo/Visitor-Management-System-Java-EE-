<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="model.VisitorRequest" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Visit Request Details</title>
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
  <h2>Visit Request Details</h2>
<%
  VisitorRequest vr = (VisitorRequest) request.getAttribute("visitRequest");
  if(vr == null) {
%>
  <p>No details available.</p>
<%
  } else {
%>
  <p><strong>Visitor Name:</strong> <%= vr.getVisitorName() %></p>
  <p><strong>Gender:</strong> <%= vr.getGender() %></p>
  <p><strong>Phone:</strong> <%= vr.getPhone() %></p>
  <p><strong>IC:</strong> <%= vr.getIc() %></p>
  <p><strong>Email:</strong> <%= vr.getEmail() %></p>
  <p><strong>Address:</strong> <%= vr.getAddress() %></p>
  <p><strong>Reason for Visit:</strong> <%= vr.getReason() %></p>
  <p><strong>Visit Date:</strong> <%= vr.getVisitDate() %></p>
  <p><strong>Status:</strong> <%= vr.getStatus() %></p>
<% } %>
  <!-- Button to return to security dashboard (for security users) -->
  <button type="button" class="link-button" onclick="location.href='securityDashboard.jsp';">
    Back To Request History
  </button>
</body>
</html>
