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

public class EditAssignmentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Assignment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edit Assignment</h1>");
            out.println("<form method=\"post\">");
            out.println("Assignment ID: <input type=\"text\" name=\"assignmentId\" required><br>");
            out.println("Title: <input type=\"text\" name=\"title\" required><br>");
            out.println("Description: <input type=\"text\" name=\"description\"><br>");
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
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String dueDateString = request.getParameter("dueDate");
            String maxScoreString = request.getParameter("maxScore");

            Date dueDate = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dueDate = dateFormat.parse(dueDateString);
            } catch (ParseException e) {
                out.println("Failed to parse due date: " + e.getMessage());
                return;
            }

            BigDecimal maxScore = null;
            try {
                maxScore = new BigDecimal(maxScoreString);
            } catch (NumberFormatException e) {
                out.println("Invalid max score format: " + e.getMessage());
                return;
            }

            Assignments assignment = new Assignments();
            assignment.setId(assignmentId);
            assignment.setTitle(title);
            assignment.setDescription(description);
            assignment.setDueDate(dueDate);
            assignment.setMaxScore(maxScore);

            ModuleImpl module = new ModuleImpl();
            try {
                Assignments updatedAssignment = module.editAssignment(assignment);
                out.println("Assignment updated successfully");
            } catch (Exception ex) {
                out.println("Failed to update assignment: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for editing an assignment";
    }
}
