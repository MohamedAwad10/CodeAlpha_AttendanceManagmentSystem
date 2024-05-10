package org.codealpha.attendancesystemwebapp.courses;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Cources;

public class DeleteCourseServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Course</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Delete Course</h1>");
            out.println("<form method=\"post\">");
            out.println("Course ID: <input type=\"text\" name=\"courseId\" required><br><br>");// Adding email field
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
            int courseId = Integer.parseInt(request.getParameter("courseId"));

            ModuleImpl module = new ModuleImpl();
            Cources course = new Cources();
            course.setId(courseId);

            try {
                course = module.findCourse(course);
                if (course == null) {
                    out.println("Course not found with ID: " + courseId);
                    return;
                }
            } catch (Exception ex) {
                out.println("Failed to retrieve course details: " + ex.getMessage());
                return;
            }

            try {
                module.deleteCourse(course);
                out.println("Course deleted successfully");
            } catch (Exception ex) {
                out.println("Failed to delete course: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for deleting a course";
    }
}
