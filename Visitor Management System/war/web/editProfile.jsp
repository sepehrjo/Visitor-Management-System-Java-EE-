<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="model.Member" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Profile</title>
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
    .container {
      max-width: 500px;
      margin: 20px auto;
    }
    .form-group { margin-bottom: 15px; }
    label { display: block; margin-bottom: 5px; }
    input, select { width: 100%; padding: 8px; }
    .error { color: red; }
    .success { color: green; }
  </style>
</head>
<body>
<%
  Member member = (Member) session.getAttribute("loggedInMember");
  if(member == null){
    response.sendRedirect("login.jsp");
    return;
  }
  // Determine back link based on user role
  String backLink = "index.jsp";
  if(member.getRole().equalsIgnoreCase("resident")) {
    backLink = "residentDashboard.jsp";
  } else if(member.getRole().equalsIgnoreCase("security")) {
    backLink = "securityDashboard.jsp";
  } else if(member.getRole().equalsIgnoreCase("staff")) {
    backLink = "managingDashboard.jsp";
  }
%>
<div class="container">
  <h2>Edit Profile</h2>
  <% 
    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");
    if(error != null){ %>
      <p class="error"><%= error %></p>
  <% } 
    if(message != null){ %>
      <p class="success"><%= message %></p>
  <% } %>
  <form action="EditProfileServlet" method="post">
    <div class="form-group">
      <label>Name:</label>
      <input type="text" name="name" value="<%= member.getName() %>" required />
    </div>
    <div class="form-group">
      <label>Password:</label>
      <input type="password" name="password" value="<%= member.getPassword() %>" required />
    </div>
    <div class="form-group">
      <label>Gender:</label>
      <select name="gender" required>
        <option value="male" <%= "male".equalsIgnoreCase(member.getGender()) ? "selected" : "" %>>Male</option>
        <option value="female" <%= "female".equalsIgnoreCase(member.getGender()) ? "selected" : "" %>>Female</option>
        <option value="other" <%= "other".equalsIgnoreCase(member.getGender()) ? "selected" : "" %>>Other</option>
      </select>
    </div>
    <div class="form-group">
      <label>Phone:</label>
      <input type="text" name="phone" value="<%= member.getPhone() %>" required />
    </div>
    <div class="form-group">
      <label>IC:</label>
      <input type="text" name="ic" value="<%= member.getIc() %>" required />
    </div>
    <div class="form-group">
      <label>Email:</label>
      <input type="email" name="email" value="<%= member.getEmail() %>" required />
    </div>
    <div class="form-group">
      <label>Address:</label>
      <input type="text" name="address" value="<%= member.getAddress() %>" required />
    </div>
    <input type="submit" class="button" value="Update Profile" />
    <button type="button" class="link-button" onclick="location.href='<%= backLink %>';">
      ← Back
    </button>
  </form>
</div>
</body>
</html>
