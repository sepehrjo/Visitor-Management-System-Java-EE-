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

@WebServlet("/ViewRequestsServlet")
public class ViewRequestsServlet extends HttpServlet {

    @EJB
    private VisitorRequestFacade visitorRequestFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Member resident = (Member) session.getAttribute("loggedInMember");

        if (resident == null || !resident.getRole().equalsIgnoreCase("resident")) {
            // Redirect to login with return URL
            response.sendRedirect("login.jsp?redirect=ViewRequestsServlet");
            return;
        }

        // Fetch requests
        List<VisitorRequest> requests = visitorRequestFacade.findByResident(resident);
        request.setAttribute("requests", requests);

        // Forward to JSP
        request.getRequestDispatcher("/viewRequest.jsp").forward(request, response);
    }
}