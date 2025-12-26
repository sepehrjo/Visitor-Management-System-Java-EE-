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

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
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
            Long userId = Long.parseLong(request.getParameter("userId"));
            Member user = memberFacade.find(userId);

            if (user == null) {
                throw new ServletException("User not found");
            }

            // Update all fields
            user.setName(request.getParameter("name"));
            user.setPassword(request.getParameter("password"));
            user.setRole(request.getParameter("role"));
            user.setGender(request.getParameter("gender"));
            user.setPhone(Long.parseLong(request.getParameter("phone")));
            user.setIc(request.getParameter("ic"));
            user.setEmail(request.getParameter("email"));
            user.setAddress(request.getParameter("address"));

            memberFacade.edit(user); // This merges changes

            response.sendRedirect("ManageUsersServlet?message=User+updated+successfully");
            
        } catch (NumberFormatException e) {
            response.sendRedirect("ManageUsersServlet?error=Invalid+phone+number+format");
        } catch (Exception e) {
            response.sendRedirect("ManageUsersServlet?error=Error+updating+user: " + URLEncoder.encode(e.getMessage(), "UTF-8"));
        }
    }
}