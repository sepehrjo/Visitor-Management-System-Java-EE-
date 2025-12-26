/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet("/ManageUsersServlet")
public class ManageUsersServlet extends HttpServlet {
    @EJB private MemberFacade memberFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        // Authorization check
        Member staff = (Member) request.getSession().getAttribute("loggedInMember");
        if(staff == null || !staff.getRole().equalsIgnoreCase("staff")) {
            response.sendRedirect("login.jsp");
            return;
        }

        String searchQuery = request.getParameter("search");
        List<Member> users;

        if(searchQuery != null && !searchQuery.isEmpty()) {
            users = memberFacade.searchByName(searchQuery);
        } else {
            users = memberFacade.findAllActive();
        }

        request.setAttribute("users", users);
        request.getRequestDispatcher("/manageUsers.jsp").forward(request, response);
    }
}