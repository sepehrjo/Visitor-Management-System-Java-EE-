package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Member;
import model.VisitorRequest;
import model.VisitorRequestFacade;

@WebServlet("/UpdateVisitorStatusServlet")
public class UpdateVisitorStatusServlet extends HttpServlet {

    @EJB
    private VisitorRequestFacade visitorRequestFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Authorization check
        HttpSession session = request.getSession();
        Member security = (Member) session.getAttribute("loggedInMember");
        if (security == null || !security.getRole().equalsIgnoreCase("security")) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Get request ID and new status
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            String newStatus = request.getParameter("status");

            // Validate input
            if (requestId == null || newStatus == null || newStatus.trim().isEmpty()) {
                session.setAttribute("error", "Invalid request parameters.");
                response.sendRedirect("ViewRequestsForSecurityServlet");
                return;
            }

            // Find and update the request
            VisitorRequest vr = visitorRequestFacade.find(requestId);
            if (vr == null) {
                session.setAttribute("error", "Request not found.");
                response.sendRedirect("ViewRequestsForSecurityServlet");
                return;
            }

            vr.setStatus(newStatus);
            visitorRequestFacade.edit(vr);

            // Success message
            session.setAttribute("message", "Request status updated successfully.");
            response.sendRedirect("ViewRequestsForSecurityServlet");

        } catch (Exception e) {
            session.setAttribute("error", "Error updating request: " + e.getMessage());
            response.sendRedirect("ViewRequestsForSecurityServlet");
        }
    }
}