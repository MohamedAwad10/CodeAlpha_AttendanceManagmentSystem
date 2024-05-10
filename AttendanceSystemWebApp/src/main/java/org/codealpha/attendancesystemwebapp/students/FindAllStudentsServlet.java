package org.codealpha.attendancesystemwebapp.students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Students;

public class FindAllStudentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModuleImpl module = new ModuleImpl();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>All Students</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>All Students</h1>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
        out.println("<th>Email</th>");
        out.println("<th>Phone Number</th>");
        out.println("<th>Enrollment Date</th>");
        out.println("</tr>");
        try {
            List<Students> students = module.findAllStudents();
            for (Students student : students) {
                out.println("<tr>");
                out.println("<td>" + student.getId() + "</td>");
                out.println("<td>" + student.getFirstName() + "</td>");
                out.println("<td>" + student.getLastName() + "</td>");
                out.println("<td>" + student.getEmail() + "</td>");
                out.println("<td>" + student.getPhoneNumber() + "</td>");
                out.println("<td>" + student.getEnrollmentDate() + "</td>");
                out.println("</tr>");
            }
        } catch (Exception ex) {
            out.println("<tr><td colspan=\"6\">Failed to retrieve students: " + ex.getMessage() + "</td></tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
