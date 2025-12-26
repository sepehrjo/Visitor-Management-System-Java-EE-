<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>System Reports</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <style>
    .report-section { margin: 20px 0; padding: 15px; border: 1px solid #ddd; }
    table { width: 100%; border-collapse: collapse; margin-top: 10px; }
    th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
    th { background-color: #f8f9fa; }
    .no-data { color: #666; padding: 20px; }
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
  </style>
</head>
<body>
  <h1>System Analytics Reports</h1>
  <button type="button" class="link-button" onclick="location.href='managingDashboard.jsp';">
    ← Back to Dashboard
  </button>

  <!-- Gender Report -->
  <div class="report-section">
    <h2>Gender Distribution</h2>
    <c:choose>
      <c:when test="${not empty genderReport}">
        <table>
          <tr><th>Gender</th><th>Total Users</th></tr>
          <c:forEach items="${genderReport}" var="report">
            <tr>
              <td>${not empty report[0] ? report[0] : 'Unknown'}</td>
              <td>${report[1]}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:otherwise>
        <p class="no-data">No gender data available</p>
      </c:otherwise>
    </c:choose>
  </div>

  <!-- Age Report -->
  <div class="report-section">
    <h2>Age Distribution</h2>
    <c:choose>
      <c:when test="${not empty ageReport}">
        <table>
          <tr><th>Age Group</th><th>Total Users</th></tr>
          <c:forEach items="${ageReport}" var="report">
            <tr>
              <td>${not empty report[0] ? report[0] : 'All'}</td>
              <td>${report[1]}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:otherwise>
        <p class="no-data">No age data available</p>
      </c:otherwise>
    </c:choose>
  </div>

  <!-- Location Report -->
  <div class="report-section">
    <h2>Geographical Distribution</h2>
    <c:choose>
      <c:when test="${not empty locationReport}">
        <table>
          <tr><th>Location</th><th>Total Users</th></tr>
          <c:forEach items="${locationReport}" var="report">
            <tr>
              <td>${not empty report[0] ? report[0] : 'Unknown'}</td>
              <td>${report[1]}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:otherwise>
        <p class="no-data">No location data available</p>
      </c:otherwise>
    </c:choose>
  </div>

  <!-- Role Report -->
  <div class="report-section">
    <h2>Role Distribution</h2>
    <c:choose>
      <c:when test="${not empty roleReport}">
        <table>
          <tr><th>User Role</th><th>Total Users</th></tr>
          <c:forEach items="${roleReport}" var="report">
            <tr>
              <td>${not empty report[0] ? report[0] : 'Unknown'}</td>
              <td>${report[1]}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:otherwise>
        <p class="no-data">No role data available</p>
      </c:otherwise>
    </c:choose>
  </div>

  <!-- Visit Status Report -->
  <div class="report-section">
    <h2>Visit Request Statuses</h2>
    <c:choose>
      <c:when test="${not empty visitStatusReport}">
        <table>
          <tr><th>Status</th><th>Total Requests</th></tr>
          <c:forEach items="${visitStatusReport}" var="report">
            <tr>
              <td>${not empty report[0] ? report[0] : 'Unknown'}</td>
              <td>${report[1]}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:otherwise>
        <p class="no-data">No visit request data available</p>
      </c:otherwise>
    </c:choose>
  </div>

  <!-- Error Display -->
  <c:if test="${not empty error}">
    <div class="error-message" style="color: white; background-color: black; padding: 20px;">
      Error: ${error}
    </div>
  </c:if>
</body>
</html>
