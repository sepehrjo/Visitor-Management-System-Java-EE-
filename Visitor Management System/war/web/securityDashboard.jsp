<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="model.Member" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Security Dashboard</title>
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
    ul { list-style-type: none; padding: 0; }
    li { margin: 10px 0; }
  </style>
</head>
<body>
  <%
    Member member = (Member) session.getAttribute("loggedInMember");
    if(member == null || !member.getRole().equalsIgnoreCase("security")){
      response.sendRedirect("login.jsp?error=Unauthorized access");
      return;
    }
  %>
  <h1>Security Dashboard</h1>
  <p>Welcome, <%= member.getName() %>!</p>
  <button type="button" class="link-button" onclick="location.href='LogoutServlet';">Logout</button>
  <hr/>
  <ul>
    <li>
      <button type="button" class="link-button" onclick="location.href='editProfile.jsp';">
        Edit Profile
      </button>
    </li>
    <li>
      <button type="button" class="link-button" onclick="location.href='assistVisitor.jsp';">
        Assist Visitor Login
      </button>
    </li>
    <li>
      <button type="button" class="link-button" onclick="location.href='recordVisitor.jsp';">
        Record Visitor Details
      </button>
    </li>
    <li>
      <button type="button" class="link-button" onclick="location.href='ViewRequestsForSecurityServlet';">
        Update Visit Request Status
      </button>
    </li>
  </ul>
</body>
</html>
