package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.VisitorRequest;
import model.VisitorRequestFacade;

@WebServlet(name="AssistVisitorServlet", urlPatterns={"/AssistVisitorServlet"})
public class AssistVisitorServlet extends HttpServlet {
    
    @EJB
    private VisitorRequestFacade visitorRequestFacade;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String visitorName = request.getParameter("visitorUsername");
        String visitorPassword = request.getParameter("visitorPassword");

        if(visitorName == null || visitorName.trim().isEmpty() ||
           visitorPassword == null || visitorPassword.trim().isEmpty()){
            request.setAttribute("error", "Please provide visitor credentials.");
            request.getRequestDispatcher("assistVisitor.jsp").forward(request, response);
            return;
        }

        // ✅ FIX: Use the new `findByVisitorName()` method
        VisitorRequest visitor = visitorRequestFacade.findByVisitorName(visitorName);
        if(visitor != null && visitor.getVisitorPassword().equals(visitorPassword)){
            // Redirect to visitor's request details
            response.sendRedirect(request.getContextPath() + "/ViewRequestDetailServlet?requestId=" + visitor.getId());
        } else {
            request.setAttribute("error", "User not found.");
            request.getRequestDispatcher("assistVisitor.jsp").forward(request, response);
        }
    }
}
