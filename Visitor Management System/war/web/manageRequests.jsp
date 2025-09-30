<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Visitor Requests</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px;
        }
        th, td { 
            padding: 12px; 
            text-align: left; 
            border-bottom: 1px solid #ddd; 
        }
        th { 
            background-color: #f8f9fa; 
        }
        .no-data { 
            color: #666; 
            padding: 20px; 
        }
        /* Unified button style: black background with white text */
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
    <h1>Manage Visitor Requests</h1>
    <button type="button" class="link-button" onclick="location.href='managingDashboard.jsp';">
        ← Back to Dashboard
    </button>

    <!-- Error/Success Messages -->
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>

    <!-- Requests Table -->
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
                            <form action="UpdateRequestStatusServlet" method="post" style="display: inline;">
                                <input type="hidden" name="requestId" value="${request.id}">
                                <select name="status">
                                    <option value="Pending" ${request.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                    <option value="Approved" ${request.status == 'Approved' ? 'selected' : ''}>Approved</option>
                                    <option value="Rejected" ${request.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
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
</body>
</html>
