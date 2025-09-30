package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Member;
import model.VisitorRequest;
import model.VisitorRequestFacade;

@WebServlet("/ViewAllRequestsServlet")
public class ViewAllRequestsServlet extends HttpServlet {

    @EJB
    private VisitorRequestFacade requestFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Authorization check
        HttpSession session = request.getSession();
        Member staff = (Member) session.getAttribute("loggedInMember");
        if(staff == null || !staff.getRole().equalsIgnoreCase("staff")) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get all requests
        List<VisitorRequest> allRequests = requestFacade.findAll();
        request.setAttribute("requests", allRequests);
        request.getRequestDispatcher("/manageRequests.jsp").forward(request, response);
    }
}