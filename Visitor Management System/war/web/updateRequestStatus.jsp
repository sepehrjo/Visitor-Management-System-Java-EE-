<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List, model.VisitorRequest" %>
<!DOCTYPE html>
<html>
<head>
    <head>
    <meta charset="UTF-8">
    <title>Page Title</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

    <meta charset="UTF-8">
    <title>Update Visit Request Status</title>
</head>
<body>

<h2>Update Visit Request Status</h2>

<!-- ✅ Display messages -->
<%
    String error = (String) request.getAttribute("error");
    String message = (String) session.getAttribute("message"); 
    session.removeAttribute("message");

    if(error != null){ 
%>
    <p style="color:red;"><%= error %></p>
<% } 
   if(message != null){ 
%>
    <p style="color:green;"><%= message %></p>
<% } 

    List<VisitorRequest> requests = (List<VisitorRequest>) request.getAttribute("requests");
    if(requests == null || requests.isEmpty()){ 
%>
    <p>No visitor requests found.</p>
<% } else { %>
    <table border="1">
        <tr>
            <th>Visitor Name</th>
            <th>Visit Date</th>
            <th>Current Status</th>
            <th>Update Status</th>
        </tr>
        <% for(VisitorRequest vr : requests){ %>
        <tr>
            <td><%= vr.getVisitorName() %></td>
            <td><%= vr.getVisitDate() %></td>
            <td><%= vr.getStatus() %></td>
            <td>
                <form action="UpdateRequestStatusServlet" method="post">
                    <input type="hidden" name="requestId" value="<%= vr.getId() %>"/>
                    <select name="status">
                        <option value="Reached">Reached</option>
                        <option value="No changes after 1 try">No changes after 1 try</option>
                        <option value="No changes after 2 tries">No changes after 2 tries</option>
                        <option value="No changes after 3 tries">No changes after 3 tries</option>
                    </select>
                    <input type="submit" value="Update Status"/>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
<% } %>
</body>
</html>
