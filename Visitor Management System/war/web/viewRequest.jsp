<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Visit Requests</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
    th { background-color: #f8f9fa; }
    .no-requests { color: #666; padding: 20px; }
    .message { padding: 10px; margin-bottom: 20px; }
    .success { background-color: #d4edda; color: #155724; }
    .error { background-color: #f8d7da; color: #721c24; }
    .link-button {
      display: inline-block;
      padding: 10px 20px;
      margin: 10px 5px;
      background-color: black;
      color: white;
      border: none;
      font-size: 14px;
      cursor: pointer;
    }
    .link-button:hover {
      background-color: #333;
    }
    .submit-button {
      background-color: black;
      color: white;
      padding: 10px 20px;
      border: none;
      font-size: 14px;
      cursor: pointer;
    }
    .submit-button:hover {
      background-color: #333;
    }
  </style>
</head>
<body>
  <h2>Your Visit Requests</h2>
  
  <!-- Display messages -->
  <c:if test="${not empty message}">
    <div class="message success">${message}</div>
  </c:if>
  <c:if test="${not empty error}">
    <div class="message error">${error}</div>
  </c:if>

  <c:choose>
    <c:when test="${not empty requests}">
      <table>
        <tr>
          <th>Visitor Name</th>
          <th>Visit Date</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
        <c:forEach items="${requests}" var="request">
          <tr>
            <td>${request.visitorName}</td>
            <td>${request.visitDate}</td>
            <td>${request.status}</td>
            <td>
              <c:if test="${request.status == 'Submitted'}">
                <!-- Cancel Form -->
                <form action="UpdateRequestStatusServlet" method="post" style="display: inline;">
                  <input type="hidden" name="requestId" value="${request.id}">
                  <input type="hidden" name="status" value="Cancelled">
                  <button type="submit" class="submit-button">Cancel</button>
                </form>
                <!-- Close Form -->
                <form action="UpdateRequestStatusServlet" method="post" style="display: inline;">
                  <input type="hidden" name="requestId" value="${request.id}">
                  <input type="hidden" name="status" value="Closed">
                  <button type="submit" class="submit-button">Close</button>
                </form>
              </c:if>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:when>
    <c:otherwise>
      <div class="no-requests">
        <p>No visit requests found.</p>
        <button type="button" class="link-button" onclick="location.href='submitRequest.jsp';">
          Submit a new request?
        </button>
      </div>
    </c:otherwise>
  </c:choose>
  
  <br>
  <button type="button" class="link-button" onclick="location.href='residentDashboard.jsp';">
    Back to Dashboard
  </button>
</body>
</html>
