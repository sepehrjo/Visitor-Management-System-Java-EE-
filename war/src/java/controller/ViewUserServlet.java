/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/ViewUserServlet")
public class ViewUserServlet extends HttpServlet {
    @EJB private MemberFacade memberFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
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
            
            if(user == null) {
                response.sendRedirect("ManageUsersServlet?error=User+not+found");
                return;
            }
            
            request.setAttribute("user", user);
            request.getRequestDispatcher("/editUser.jsp").forward(request, response);
            
        } catch(Exception e) {
            response.sendRedirect("ManageUsersServlet?error=Invalid+user+ID");
        }
    }
}