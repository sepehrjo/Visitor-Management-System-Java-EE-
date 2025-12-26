package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.VisitorRequest;
import model.VisitorRequestFacade;

@WebServlet(name="ViewRequestDetailServlet", urlPatterns={"/ViewRequestDetailServlet"})
public class ViewRequestDetailServlet extends HttpServlet {
    
    @EJB
    private VisitorRequestFacade visitorRequestFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestIdStr = request.getParameter("requestId");
        if(requestIdStr == null || requestIdStr.trim().isEmpty()){
            response.sendRedirect(request.getContextPath() + "/ViewRequestsServlet");
            return;
        }
        Long requestId = Long.valueOf(requestIdStr);
        VisitorRequest vr = visitorRequestFacade.find(requestId);
        if(vr == null){
            request.setAttribute("error", "Request not found.");
            request.getRequestDispatcher("/viewRequestDetail.jsp").forward(request, response);
            return;
        }
        request.setAttribute("visitRequest", vr);
        request.getRequestDispatcher("/viewRequestDetail.jsp").forward(request, response);
    }
}
