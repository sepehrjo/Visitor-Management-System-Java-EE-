<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add New User</title>
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
    .container { max-width: 500px; margin: 20px auto; }
    .form-group { margin-bottom: 15px; }
    label { display: block; margin-bottom: 5px; }
    input, select, textarea { width: 100%; padding: 8px; }
    .error { color: red; }
    .success { color: green; }
  </style>
</head>
<body>
  <div class="container">
    <h2>Add New User</h2>
    <c:if test="${not empty error}">
      <p class="error">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
      <p class="success">${message}</p>
    </c:if>
    <form action="AddUserServlet" method="post">
      <div class="form-group">
        <label>Name:</label>
        <input type="text" name="name" required>
      </div>
      <div class="form-group">
        <label>Password:</label>
        <input type="password" name="password" required>
      </div>
      <div class="form-group">
        <label>Role:</label>
        <select name="role" required>
          <option value="resident">Resident</option>
          <option value="security">Security</option>
          <option value="staff">Staff</option>
        </select>
      </div>
      <div class="form-group">
        <label>Gender:</label>
        <select name="gender" required>
          <option value="male">Male</option>
          <option value="female">Female</option>
          <option value="other">Other</option>
        </select>
      </div>
      <div class="form-group">
        <label>Phone:</label>
        <input type="text" name="phone" required>
      </div>
      <div class="form-group">
        <label>IC Number:</label>
        <input type="text" name="ic" required>
      </div>
      <div class="form-group">
        <label>Email:</label>
        <input type="email" name="email" required>
      </div>
      <div class="form-group">
        <label>Address:</label>
        <textarea name="address" required></textarea>
      </div>
      <div class="form-group">
        <input type="submit" class="button" value="Add User">
        <button type="button" class="link-button" onclick="location.href='managingDashboard.jsp';">
          ← Back
        </button>
      </div>
    </form>
  </div>
</body>
</html>
