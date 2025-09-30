<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Update Visit Request Status</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <style>
    table { width: 100%; border-collapse: collapse; }
    th, td { padding: 8px; border: 1px solid #ddd; }
    .no-data { color: #666; padding: 20px; }
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
  <h2>Update Visit Request Status</h2>
  
  <!-- Display error/success messages -->
  <c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
  </c:if>
  <c:if test="${not empty message}">
    <p style="color:white; background-color:black; padding:10px;">${message}</p>
  </c:if>

  <c:choose>
    <c:when test="${not empty requests}">
      <table>
        <tr>
          <th>Visitor Name</th>
          <th>Resident</th>
          <th>Visit Date</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
        <c:forEach items="${requests}" var="request">
          <tr>
            <td>${request.visitorName}</td>
            <td>${request.resident.name}</td>
            <td>${request.visitDate}</td>
            <td>${request.status}</td>
            <td>
              <form action="UpdateVisitorStatusServlet" method="post">
                <input type="hidden" name="requestId" value="${request.id}">
                <select name="status">
                  <option value="Approved">Approve</option>
                  <option value="Rejected">Reject</option>
                  <option value="Pending">Pending</option>
                </select>
                <button type="submit" class="button">Update</button>
              </form>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:when>
    <c:otherwise>
      <p class="no-data">No visitor requests found.</p>
    </c:otherwise>
  </c:choose>
  
  <br>
  <button type="button" class="link-button" onclick="location.href='securityDashboard.jsp';">
    Back to Dashboard
  </button>
</body>
</html>
