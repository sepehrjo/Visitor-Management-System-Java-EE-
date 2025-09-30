<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="model.Member" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Resident Dashboard</title>
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
    if(member == null || !member.getRole().equalsIgnoreCase("resident")){
      response.sendRedirect("login.jsp?error=Unauthorized access");
      return;
    }
  %>
  <h1>Resident Dashboard</h1>
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
      <button type="button" class="link-button" onclick="location.href='submitRequest.jsp';">
        Submit Visit Request
      </button>
    </li>
    <li>
      <button type="button" class="link-button" onclick="location.href='ViewRequestsServlet';">
        View Request History
      </button>
    </li>
  </ul>
</body>
</html>
