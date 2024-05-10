package org.codealpha.attendancesystemwebapp.assignments;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Assignments;

public class DeleteAssignmentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Assignment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Delete Assignment</h1>");
            out.println("<form method=\"post\">");
            out.println("Assignment ID: <input type=\"text\" name=\"assignmentId\" required><br><br>");
            out.println("<input type=\"submit\" value=\"Delete\">");
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

            ModuleImpl module = new ModuleImpl();
            Assignments assignment = new Assignments();
            assignment.setId(assignmentId);
            try {
                assignment = module.findAssignment(assignment);
                if (assignment == null) {
                    out.println("Assignment not found with ID: " + assignmentId);
                    return;
                }
            } catch (Exception ex) {
                out.println("Failed to retrieve assignment details: " + ex.getMessage());
                return;
            }

            try {
                module.deleteAssignment(assignment);
                out.println("Assignment deleted successfully");
            } catch (Exception ex) {
                out.println("Failed to delete assignment: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for deleting an assignment";
    }
}
