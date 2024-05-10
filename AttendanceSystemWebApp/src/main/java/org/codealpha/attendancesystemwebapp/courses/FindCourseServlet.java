package org.codealpha.attendancesystemwebapp.courses;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Cources;

public class FindCourseServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Find Course</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Find Course</h1>");
            out.println("<form method=\"post\">");
            out.println("Course ID: <input type=\"text\" name=\"courseId\" required><br>");
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
            // Extracting course ID from request parameters
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Cources course = new Cources();
            course.setId(courseId);

            // Fetching course details using the provided course ID
            ModuleImpl module = new ModuleImpl();
            try {
                Cources foundCourse = module.findCourse(course);
                if (foundCourse != null) {
                    // Displaying course details
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Course Details</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Course Details</h1>");
                    out.println("<p>Course ID: " + foundCourse.getId() + "</p>");
                    out.println("<p>Course Name: " + foundCourse.getCourseName() + "</p>");
                    out.println("<p>Instructor: " + foundCourse.getInstructor() + "</p>");
                    out.println("<p>Start Date: " + foundCourse.getStartDate() + "</p>");
                    out.println("<p>End Date: " + foundCourse.getEndDate() + "</p>");
                    out.println("<p>Location: " + foundCourse.getLocation() + "</p>");
                    out.println("<p>Capacity: " + foundCourse.getCapacity() + "</p>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    out.println("Course not found.");
                }
            } catch (Exception ex) {
                out.println("Failed to fetch course details: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for finding a course by ID";
    }
}
