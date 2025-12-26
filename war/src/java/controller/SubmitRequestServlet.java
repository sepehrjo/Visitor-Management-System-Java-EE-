package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Member;
import model.VisitorRequest;
import model.VisitorRequestFacade;

@WebServlet(name="SubmitRequestServlet", urlPatterns={"/SubmitRequestServlet"})
public class SubmitRequestServlet extends HttpServlet {
    
    @EJB
    private VisitorRequestFacade visitorRequestFacade;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Member resident = (Member) session.getAttribute("loggedInMember");
            if(resident == null){
                response.sendRedirect("login.jsp");
                return;
            }
            
            // Retrieve visitor details from the form
            String visitorName = request.getParameter("visitorName");
            String visitorPassword = request.getParameter("visitorPassword");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String ic = request.getParameter("ic");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String reason = request.getParameter("reason");
            String visitDateStr = request.getParameter("visitDate");

            if(visitorName.isEmpty() || visitorPassword.isEmpty() || gender.isEmpty() || 
               phone.isEmpty() || ic.isEmpty() || email.isEmpty() || address.isEmpty() || 
               reason.isEmpty() || visitDateStr.isEmpty()){
                request.setAttribute("error", "All fields are required.");
                request.getRequestDispatcher("submitRequest.jsp").forward(request, response);
                return;
            }
            
            Date visitDate = new SimpleDateFormat("yyyy-MM-dd").parse(visitDateStr);
            VisitorRequest vr = new VisitorRequest(visitorName, visitorPassword, gender, phone, ic, email, address, visitDate, reason, resident);
            visitorRequestFacade.create(vr);

            // **NEW: Store success message in request attribute**
            request.setAttribute("message", "Visit request has been submitted successfully.");

            request.getRequestDispatcher("submitRequest.jsp").forward(request, response);
        } catch(ParseException ex) {
            request.setAttribute("error", "Invalid date format.");
            request.getRequestDispatcher("submitRequest.jsp").forward(request, response);
        }
    }
}

