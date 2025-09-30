<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Management</title>
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
    table { width: 100%; border-collapse: collapse; }
    th, td { padding: 8px; border: 1px solid #ddd; }
  </style>
</head>
<body>
  <h1>User Management</h1>
  
  <c:if test="${not empty param.message}">
    <p style="color:green;">${param.message}</p>
  </c:if>
  <c:if test="${not empty param.error}">
    <p style="color:red;">${param.error}</p>
  </c:if>

  <form action="ManageUsersServlet" method="get">
    <input type="text" name="search" placeholder="Search by name...">
    <input type="submit" class="button" value="Search">
    <button type="button" class="link-button" onclick="location.href='ManageUsersServlet';">
      Show All
    </button>
  </form>

  <button type="button" class="link-button" onclick="location.href='addUser.jsp';">
    Add New User
  </button>

  <table>
    <tr>
      <th>Name</th>
      <th>Role</th>
      <th>Email</th>
      <th>Phone</th>
      <th>Actions</th>
    </tr>
    <c:forEach items="${users}" var="user">
      <tr>
        <td>${user.name}</td>
        <td>${user.role}</td>
        <td>${user.email}</td>
        <td>${user.phone}</td>
        <td>
          <form action="DeleteUserServlet" method="post" style="display:inline;">
            <input type="hidden" name="userId" value="${user.id}">
            <button type="submit" class="button">Delete</button>
          </form>
          <button type="button" class="link-button" onclick="location.href='ViewUserServlet?userId=${user.id}';">
            Edit
          </button>
        </td>
      </tr>
    </c:forEach>
  </table>
  <button type="button" class="link-button" onclick="location.href='managingDashboard.jsp';">
    ← Back
  </button>
</body>
</html>
