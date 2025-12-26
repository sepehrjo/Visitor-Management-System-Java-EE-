package controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.ReportFacade;
import model.Member;

@WebServlet("/ReportsServlet")
public class ReportsServlet extends HttpServlet {

    @EJB
    private ReportFacade reportFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInMember") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Member user = (Member) session.getAttribute("loggedInMember");
        if (!"staff".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("login.jsp?error=Unauthorized");
            return;
        }

        try {
            // Gender
            List<Object[]> genderData = reportFacade.getGenderReport();
            System.out.println("[DEBUG] Gender data: " + genderData);
            request.setAttribute("genderReport", genderData);

            // Age
            List<Object[]> ageData = reportFacade.getAgeReport();
            System.out.println("[DEBUG] Age data: " + ageData);
            request.setAttribute("ageReport", ageData);

            // Location
            List<Object[]> locationData = reportFacade.getLocationReport();
            System.out.println("[DEBUG] Location data: " + locationData);
            request.setAttribute("locationReport", locationData);

            // Role
            List<Object[]> roleData = reportFacade.getRoleReport();
            System.out.println("[DEBUG] Role data: " + roleData);
            request.setAttribute("roleReport", roleData);

            // Visit Status
            List<Object[]> visitStatusData = reportFacade.getVisitStatusReport();
            System.out.println("[DEBUG] Visit status data: " + visitStatusData);
            request.setAttribute("visitStatusReport", visitStatusData);

        } catch (Exception e) {
            System.err.println("Error generating reports: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Failed to generate reports: " + e.getMessage());
        }

        request.getRequestDispatcher("/reports.jsp").forward(request, response);
    }
}