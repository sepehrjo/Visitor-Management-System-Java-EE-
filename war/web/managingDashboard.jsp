<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Member" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Managing Staff Dashboard</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <style>
    /* Container for center alignment */
    .container {
      max-width: 800px;
      margin: 0 auto;
      text-align: center;
      padding: 20px;
    }
    .dashboard-section {
      margin: 20px 0;
    }
    ul {
      list-style-type: none;
      padding: 0;
      margin: 0;
    }
    li {
      margin: 10px 0;
    }
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
    hr {
      margin: 20px auto;
      width: 80%;
    }
  </style>
</head>
<body>
  <%
    Member member = (Member) session.getAttribute("loggedInMember");
    if (member == null || !member.getRole().equalsIgnoreCase("staff")) {
      response.sendRedirect("login.jsp?error=Unauthorized+access");
      return;
    }
  %>
  
  <div class="container">
    <h1>Managing Staff Dashboard</h1>
    <p>Welcome, <%= member.getName() %>!</p>
    <button type="button" class="link-button" onclick="location.href='LogoutServlet';">Logout</button>
    <hr/>

    <!-- User Management -->
    <div class="dashboard-section">
      <h2>User Management</h2>
      <ul>
        <li>
          <button type="button" class="link-button" onclick="location.href='addUser.jsp';">
            Add New User
          </button>
        </li>
        <li>
          <button type="button" class="link-button" onclick="location.href='ManageUsersServlet';">
            Manage Existing Users
          </button>
        </li>
      </ul>
    </div>

    <!-- Visitor Request Management -->
    <div class="dashboard-section">
      <h2>Visitor Request Management</h2>
      <ul>
        <li>
          <button type="button" class="link-button" onclick="location.href='ViewAllRequestsServlet';">
            View/Update All Visit Requests
          </button>
        </li>
      </ul>
    </div>

    <!-- Reports -->
    <div class="dashboard-section">
      <h2>Analytics Reports</h2>
      <ul>
        <li>
          <button type="button" class="link-button" onclick="location.href='ReportsServlet';">
            View System Reports
          </button>
        </li>
      </ul>
    </div>
  </div>
</body>
</html>
