<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Member" %>
<%
    Member user = (Member) request.getAttribute("user");
    if(user == null) {
        response.sendRedirect("ManageUsersServlet?error=User+not+found");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <head>
    <meta charset="UTF-8">
    <title>Page Title</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

    <title>Edit User</title>
    <style>
        .form-container { max-width: 500px; margin: 20px auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input, select, textarea { width: 100%; padding: 8px; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Edit User</h2>
        
        <!-- Display error/success messages -->
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p class="success">${message}</p>
        </c:if>

        <!-- Edit User Form -->
        <form action="UpdateUserServlet" method="post">
            <input type="hidden" name="userId" value="${user.id}">

            <div class="form-group">
                <label>Name:</label>
                <input type="text" name="name" value="${user.name}" required>
            </div>

            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" value="${user.password}" required>
            </div>

            <div class="form-group">
                <label>Role:</label>
                <select name="role" required>
                    <option value="resident" ${user.role == 'resident' ? 'selected' : ''}>Resident</option>
                    <option value="security" ${user.role == 'security' ? 'selected' : ''}>Security</option>
                    <option value="staff" ${user.role == 'staff' ? 'selected' : ''}>Staff</option>
                </select>
            </div>

            <div class="form-group">
                <label>Gender:</label>
                <select name="gender" required>
                    <option value="male" ${user.gender == 'male' ? 'selected' : ''}>Male</option>
                    <option value="female" ${user.gender == 'female' ? 'selected' : ''}>Female</option>
                    <option value="other" ${user.gender == 'other' ? 'selected' : ''}>Other</option>
                </select>
            </div>

            <div class="form-group">
                <label>Phone:</label>
                <input type="text" name="phone" value="${user.phone}" required>
            </div>

            <div class="form-group">
                <label>IC Number:</label>
                <input type="text" name="ic" value="${user.ic}" required>
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${user.email}" required>
            </div>

            <div class="form-group">
                <label>Address:</label>
                <textarea name="address" required>${user.address}</textarea>
            </div>

            <div class="form-group">
                <button type="submit">Update User</button>
                <a href="ManageUsersServlet">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>