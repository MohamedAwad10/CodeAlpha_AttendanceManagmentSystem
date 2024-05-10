package org.codealpha.attendancesystemwebapp.courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Cources;

public class EditCourseServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Course</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edit Course</h1>");
            out.println("<form method=\"post\">");
            out.println("Course ID: <input type=\"text\" name=\"courseId\" required><br>");
            out.println("Course Name: <input type=\"text\" name=\"courseName\" required><br>");
            out.println("Instructor: <input type=\"text\" name=\"instructor\" required><br>");
            out.println("Start Date: <input type=\"date\" name=\"startDate\" required><br>");
            out.println("End Date: <input type=\"date\" name=\"endDate\" required><br>");
            out.println("Location: <input type=\"text\" name=\"location\"><br>");
            out.println("Capacity: <input type=\"text\" name=\"capacity\" required><br>");
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
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String courseName = request.getParameter("courseName");
            String instructor = request.getParameter("instructor");
            String startDateString = request.getParameter("startDate");
            String endDateString = request.getParameter("endDate");
            String location = request.getParameter("location");
            int capacity = Integer.parseInt(request.getParameter("capacity"));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = dateFormat.parse(startDateString);
                endDate = dateFormat.parse(endDateString);
            } catch (ParseException e) {
                out.println("Failed to parse dates: " + e.getMessage());
                return;
            }

            Cources course = new Cources();
            course.setId(courseId);
            course.setCourseName(courseName);
            course.setInstructor(instructor);
            course.setStartDate(startDate);
            course.setEndDate(endDate);
            course.setLocation(location);
            course.setCapacity(capacity);

            ModuleImpl module = new ModuleImpl();
            try {
                Cources updatedCourse = module.editCourse(course);
                out.println("Course updated successfully");
            } catch (Exception ex) {
                out.println("Failed to update course: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for editing a course";
    }
}
