package org.codealpha.attendancesystemwebapp.assignments;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Assignments;

public class FindAssignmentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Find Assignment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Find Assignment</h1>");
            out.println("<form method=\"post\">");
            out.println("Assignment ID: <input type=\"text\" name=\"assignmentId\" required><br>");
            out.println("<input type=\"submit\" value=\"Find\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Extracting assignment ID from request parameters
            int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
            Assignments assignment = new Assignments();
            assignment.setId(assignmentId);

            // Fetching assignment details using the provided assignment ID
            ModuleImpl module = new ModuleImpl();
            try {
                Assignments foundAssignment = module.findAssignment(assignment);
                if (foundAssignment != null) {
                    // Displaying assignment details
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Assignment Details</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Assignment Details</h1>");
                    out.println("<p>Assignment ID: " + foundAssignment.getId() + "</p>");
                    out.println("<p>Course ID: " + foundAssignment.getCources()+ "</p>");
                    out.println("<p>Title: " + foundAssignment.getTitle() + "</p>");
                    out.println("<p>Description: " + foundAssignment.getDescription() + "</p>");
                    out.println("<p>Due Date: " + foundAssignment.getDueDate() + "</p>");
                    out.println("<p>Max Score: " + foundAssignment.getMaxScore() + "</p>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    out.println("Assignment not found.");
                }
            } catch (Exception ex) {
                out.println("Failed to fetch assignment details: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for finding an assignment by ID";
    }
}
