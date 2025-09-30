package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Member;
import model.VisitorRequest;
import model.VisitorRequestFacade;

@WebServlet("/UpdateRequestStatusServlet")
public class UpdateRequestStatusServlet extends HttpServlet {

    @EJB
    private VisitorRequestFacade visitorRequestFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Member currentUser = (Member) session.getAttribute("loggedInMember");
        
        // Allow only residents or staff to update the status
        if (currentUser == null || 
           !(currentUser.getRole().equalsIgnoreCase("resident") || currentUser.getRole().equalsIgnoreCase("staff"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Long requestId = Long.parseLong(request.getParameter("requestId"));
            String newStatus = request.getParameter("status");

            VisitorRequest vr = visitorRequestFacade.find(requestId);
            
            // If the user is a resident, ensure that they are the owner of the request.
            if (currentUser.getRole().equalsIgnoreCase("resident")) {
                if (vr == null || !vr.getResident().getId().equals(currentUser.getId())) {
                    session.setAttribute("error", "Invalid request.");
                    response.sendRedirect("ViewRequestsServlet");
                    return;
                }
            }
            // For staff, no ownership check is needed.

            // Update the status
            vr.setStatus(newStatus);
            visitorRequestFacade.edit(vr);

            session.setAttribute("message", "Status updated to: " + newStatus);
            
        } catch (Exception e) {
            session.setAttribute("error", "Update failed: " + e.getMessage());
        }
        
        // Redirect back to the appropriate view
        if (currentUser.getRole().equalsIgnoreCase("resident")) {
            response.sendRedirect("ViewRequestsServlet");
        } else if (currentUser.getRole().equalsIgnoreCase("staff")) {
            response.sendRedirect("ViewAllRequestsServlet");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
