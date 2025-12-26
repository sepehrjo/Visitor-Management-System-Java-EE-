package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Member;
import model.MemberFacade;

@WebServlet(name="EditProfileServlet", urlPatterns={"/EditProfileServlet"})
public class EditProfileServlet extends HttpServlet {
    
    @EJB
    private MemberFacade memberFacade;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Member loggedInMember = (Member) session.getAttribute("loggedInMember");
            if(loggedInMember == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            
            // Retrieve updated fields from form
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String phoneStr = request.getParameter("phone");
            String ic = request.getParameter("ic");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            
            long phone = 0;
            if(phoneStr != null && !phoneStr.trim().isEmpty()){
                try {
                    phone = Long.parseLong(phoneStr);
                } catch(NumberFormatException e) {
                    request.setAttribute("error", "Phone must be numeric.");
                    request.getRequestDispatcher("editProfile.jsp").forward(request, response);
                    return;
                }
            }
            
            // Update the logged-in member's fields
            loggedInMember.setName(name);
            loggedInMember.setPassword(password);
            loggedInMember.setGender(gender);
            loggedInMember.setPhone(phone);
            loggedInMember.setIc(ic);
            loggedInMember.setEmail(email);
            loggedInMember.setAddress(address);
            
            memberFacade.update(loggedInMember);
            session.setAttribute("loggedInMember", loggedInMember);
            request.setAttribute("message", "Profile updated successfully.");
            request.getRequestDispatcher("editProfile.jsp").forward(request, response);
        } catch(Exception ex) {
            request.setAttribute("error", "An error occurred: " + ex.getMessage());
            request.getRequestDispatcher("editProfile.jsp").forward(request, response);
        }
    }
}

