package org.codealpha.attendancesystemwebapp.assignments;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Assignments;
import org.codealpha.attendancesystem.dal.entity.Cources;

public class AddAssignmentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add Assignment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Add Assignment</h1>");
            out.println("<form method=\"post\">");
            out.println("Course ID: <input type=\"text\" name=\"courseId\" required><br>");
            out.println("Title: <input type=\"text\" name=\"title\" required><br>");
            out.println("Description: <textarea name=\"description\"></textarea><br>");
            out.println("Due Date: <input type=\"date\" name=\"dueDate\" required><br>");
            out.println("Max Score: <input type=\"text\" name=\"maxScore\" required><br>");
            out.println("<input type=\"submit\" value=\"Submit\">");
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
            // Extracting parameters from the request
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String dueDateString = request.getParameter("dueDate");
            BigDecimal maxScore = new BigDecimal(request.getParameter("maxScore"));

            // Parsing due date
            Date dueDate = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dueDate = dateFormat.parse(dueDateString);
            } catch (ParseException e) {
                out.println("Failed to parse due date: " + e.getMessage());
                return;
            }

            // Creating a new Assignment object
            Cources course = new Cources();
            course.setId(courseId);
            Assignments assignment = new Assignments(course, title, description, dueDate, maxScore);

            // Adding the assignment using ModuleImpl
            ModuleImpl module = new ModuleImpl();
            try {
                Assignments newAssignment = module.addAssignment(assignment);
                out.println("Assignment added successfully with ID: " + newAssignment.getId());
            } catch (Exception ex) {
                out.println("Failed to add assignment: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for adding a new assignment";
    }
}
