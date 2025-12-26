/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Member;
import model.MemberFacade;

/**
 *
 * @author sepehrjokanian
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    @EJB private MemberFacade memberFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        // Authorization check
        Member staff = (Member) request.getSession().getAttribute("loggedInMember");
        if(staff == null || !staff.getRole().equalsIgnoreCase("staff")) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Retrieve form data
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String ic = request.getParameter("ic");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            // Validate required fields
            if (name == null || name.isEmpty() || 
                password == null || password.isEmpty() ||
                role == null || role.isEmpty()) {
                throw new ServletException("Required fields are missing");
            }

            // Create new user
            Member newUser = new Member(
                name.trim(),
                password.trim(),
                role.trim(),
                gender != null ? gender.trim() : "",
                Long.parseLong(phone.trim()),
                ic != null ? ic.trim() : "",
                email != null ? email.trim() : "",
                address != null ? address.trim() : ""
            );

            // Save to database
            memberFacade.create(newUser);

            // Redirect with success message
            response.sendRedirect("ManageUsersServlet?message=User+added+successfully");
        } catch (Exception e) {
            // Redirect with error message
            response.sendRedirect("addUser.jsp?error=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
        }
    }
}