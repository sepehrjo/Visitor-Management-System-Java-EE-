<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <script>
    function togglePassword() {
      var pwdField = document.getElementById("password");
      pwdField.type = (pwdField.type === "password") ? "text" : "password";
    }
  </script>
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
    .field-note {
      font-size: 0.9em;
      color: #666;
    }
    .container {
      max-width: 500px;
      margin: 20px auto;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Register New Member</h2>
    <% String error = (String) request.getAttribute("error");
       if(error != null){ %>
      <p style="color:red;"><%= error %></p>
    <% } %>
    <form action="RegisterServlet" method="post">
      <label>Name:</label>
      <input type="text" name="name" placeholder="Enter your full name (letters)" /><br/>
      <span class="field-note">Example: John Doe</span><br/><br/>
      
      <label>Password:</label>
      <input type="password" id="password" name="password" placeholder="Enter your password" /><br/>
      <input type="checkbox" onclick="togglePassword()"> Show Password<br/>
      <span class="field-note">Example: abc123, 12345, or any combination</span><br/><br/>
      
      <label>Role:</label>
      <select name="role">
        <option value="resident">Resident</option>
        <option value="security">Security</option>
        <option value="staff">Staff</option>
      </select><br/>
      <span class="field-note">Select one of: Resident, Security, or Staff</span><br/><br/>
      
      <label>Gender:</label>
      <select name="gender">
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
      </select><br/>
      <span class="field-note">Select your gender</span><br/><br/>
      
      <label>Phone:</label>
      <input type="text" name="phone" placeholder="Numeric values only" /><br/>
      <span class="field-note">Example: 0123456789</span><br/><br/>
      
      <label>IC:</label>
      <input type="text" name="ic" placeholder="Enter your IC (alphanumeric allowed)" /><br/>
      <span class="field-note">Example: A1234567</span><br/><br/>
      
      <label>Email:</label>
      <input type="email" name="email" placeholder="Enter a valid email" /><br/>
      <span class="field-note">Example: john@example.com</span><br/><br/>
      
      <label>Address:</label>
      <input type="text" name="address" placeholder="Alphanumeric characters allowed" /><br/>
      <span class="field-note">Example: 123 Main Street</span><br/><br/>
      
      <input type="submit" class="button" value="Register" />
      <button type="button" class="link-button" onclick="location.href='index.jsp';">
        ← Back
      </button>
    </form>
  </div>
</body>
</html>
