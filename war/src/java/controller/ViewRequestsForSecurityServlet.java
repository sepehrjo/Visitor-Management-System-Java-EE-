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

@WebServlet("/ViewRequestsForSecurityServlet")
public class ViewRequestsForSecurityServlet extends HttpServlet {

    @EJB
    private VisitorRequestFacade visitorRequestFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Authorization check
        HttpSession session = request.getSession();
        Member security = (Member) session.getAttribute("loggedInMember");
        if (security == null || !security.getRole().equalsIgnoreCase("security")) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch all visit requests
        List<VisitorRequest> requests = visitorRequestFacade.findAll();
        System.out.println("[DEBUG] Found " + requests.size() + " visit requests.");

        if (requests != null && !requests.isEmpty()) {
            request.setAttribute("requests", requests);
        } else {
            request.setAttribute("error", "No visitor requests found.");
        }

        request.getRequestDispatcher("/updateRequestStatusForSecurity.jsp").forward(request, response);
    }
}