package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.SecurityVisitorRecord;
import model.SecurityVisitorRecordFacade;

@WebServlet(name="RecordVisitorServlet", urlPatterns={"/RecordVisitorServlet"})
public class RecordVisitorServlet extends HttpServlet {
    
    @EJB
    private SecurityVisitorRecordFacade securityVisitorRecordFacade;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String ic = request.getParameter("ic");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            // ✅ Ensure all fields are filled
            if(name.isEmpty() || password.isEmpty() || gender.isEmpty() || phone.isEmpty() ||
               ic.isEmpty() || email.isEmpty() || address.isEmpty()){
                request.setAttribute("error", "All fields are required.");
                request.getRequestDispatcher("recordVisitor.jsp").forward(request, response);
                return;
            }

            // ✅ Save visitor details in SecurityVisitorRecord
            SecurityVisitorRecord visitor = new SecurityVisitorRecord(name, password, gender, phone, ic, email, address);
            securityVisitorRecordFacade.create(visitor);

            // ✅ Success message
            request.setAttribute("message", "Visitor details saved successfully.");
            request.getRequestDispatcher("recordVisitor.jsp").forward(request, response);
        } catch(Exception ex) {
            request.setAttribute("error", "An error occurred: " + ex.getMessage());
            request.getRequestDispatcher("recordVisitor.jsp").forward(request, response);
        }
    }
}
