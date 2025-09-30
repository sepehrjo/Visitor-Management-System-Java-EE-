package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Member;
import model.MemberFacade;

@WebServlet(name="RegisterServlet", urlPatterns={"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    
    @EJB
    private MemberFacade memberFacade;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve form fields
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String gender = request.getParameter("gender");
            String phoneStr = request.getParameter("phone");
            String ic = request.getParameter("ic");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            
            // Basic field check
            if(name == null || name.trim().isEmpty() ||
               password == null || password.trim().isEmpty() ||
               role == null || role.trim().isEmpty()){
                request.setAttribute("error", "Please fill in all required fields.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            
            // Parse phone (if provided, must be numeric)
            long phone = 0;
            if(phoneStr != null && !phoneStr.trim().isEmpty()){
                try {
                    phone = Long.parseLong(phoneStr);
                } catch(NumberFormatException e) {
                    request.setAttribute("error", "Phone must be numeric.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }
            }
            
            // Ensure the member does not already exist (using findByName)
            Member existing = memberFacade.findByName(name);
            if(existing != null){
                request.setAttribute("error", "User already exists.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            
            // Create and persist the new member
            Member newMember = new Member(name, password, role, gender, phone, ic, email, address);
            memberFacade.create(newMember);
            
            // Forward to login page with success message
            request.setAttribute("message", "Registration successful. Please login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch(Exception ex) {
            request.setAttribute("error", "An error occurred: " + ex.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
