package org.codealpha.attendancesystemwebapp.assignments;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Assignments;

public class FindAllAssignmentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModuleImpl module = new ModuleImpl();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>All Assignments</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>All Assignments</h1>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Course ID</th>");
        out.println("<th>Title</th>");
        out.println("<th>Description</th>");
        out.println("<th>Due Date</th>");
        out.println("<th>Max Score</th>");
        out.println("</tr>");
        try {
            List<Assignments> assignments = module.findAllAssignments();
            for (Assignments assignment : assignments) {
                out.println("<tr>");
                out.println("<td>" + assignment.getId() + "</td>");
                out.println("<td>" + assignment.getCources()+ "</td>");
                out.println("<td>" + assignment.getTitle() + "</td>");
                out.println("<td>" + assignment.getDescription() + "</td>");
                out.println("<td>" + assignment.getDueDate() + "</td>");
                out.println("<td>" + assignment.getMaxScore() + "</td>");
                out.println("</tr>");
            }
        } catch (Exception ex) {
            out.println("<tr><td colspan=\"6\">Failed to retrieve assignments: " + ex.getMessage() + "</td></tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
