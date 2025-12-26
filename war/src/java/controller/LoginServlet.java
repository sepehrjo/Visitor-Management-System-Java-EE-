package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Member;
import model.MemberFacade;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @EJB
    private MemberFacade memberFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Validate input fields
            if (username == null || username.trim().isEmpty() 
                || password == null || password.trim().isEmpty()) {
                request.setAttribute("error", "Please fill all fields");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            Member member = memberFacade.findByName(username);

            if (member != null && member.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInMember", member);

                // Role-based redirection
                String role = member.getRole().toLowerCase();
                switch (role) {
                    case "resident":
                        response.sendRedirect("residentDashboard.jsp");
                        break;
                    case "security":
                        response.sendRedirect("securityDashboard.jsp");
                        break;
                    case "staff":
                        response.sendRedirect("managingDashboard.jsp");
                        break;
                    default:
                        request.setAttribute("error", "Invalid user role");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", "Login error: " + ex.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}