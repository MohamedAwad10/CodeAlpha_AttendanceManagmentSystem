package org.codealpha.attendancesystemwebapp.courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Cources;

public class FindAllCoursesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModuleImpl module = new ModuleImpl();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>All Courses</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>All Courses</h1>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Course Name</th>");
        out.println("<th>Instructor</th>");
        out.println("<th>Start Date</th>");
        out.println("<th>End Date</th>");
        out.println("<th>Location</th>");
        out.println("<th>Capacity</th>");
        out.println("</tr>");
        try {
            List<Cources> courses = module.findAllCourses();
            for (Cources course : courses) {
                out.println("<tr>");
                out.println("<td>" + course.getId() + "</td>");
                out.println("<td>" + course.getCourseName() + "</td>");
                out.println("<td>" + course.getInstructor() + "</td>");
                out.println("<td>" + course.getStartDate() + "</td>");
                out.println("<td>" + course.getEndDate() + "</td>");
                out.println("<td>" + course.getLocation() + "</td>");
                out.println("<td>" + course.getCapacity() + "</td>");
                out.println("</tr>");
            }
        } catch (Exception ex) {
            out.println("<tr><td colspan=\"7\">Failed to retrieve courses: " + ex.getMessage() + "</td></tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
