<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>All Visit Requests</title>
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
    table { width: 100%; border-collapse: collapse; }
    th, td { padding: 12px; border: 1px solid #ddd; }
  </style>
</head>
<body>
  <h2>All Visit Requests</h2>
  <!-- Back button -->
  <button type="button" class="link-button" onclick="location.href='managingDashboard.jsp';">
    ← Back to Dashboard
  </button>
  
  <c:if test="${not empty requests}">
    <table>
      <tr>
        <th>Visitor Name</th>
        <th>Resident</th>
        <th>Visit Date</th>
        <th>Status</th>
      </tr>
      <c:forEach items="${requests}" var="request">
        <tr>
          <td>${request.visitorName}</td>
          <td>${request.resident.name}</td>
          <td>${request.visitDate}</td>
          <td>${request.status}</td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
</body>
</html>
