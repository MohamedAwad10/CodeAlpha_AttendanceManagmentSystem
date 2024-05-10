package org.codealpha.attendancesystemwebapp.students;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Students;

public class FindStudentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Find Student</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Find Student</h1>");
            out.println("<form method=\"post\">");
            out.println("Student ID: <input type=\"text\" name=\"studentId\" required><br>");
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
            // Extracting student ID from request parameters
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            Students student = new Students();
            student.setId(studentId);
//            student.setEmail(student.getEmail());
//            student.setEnrollmentDate(student.getEnrollmentDate());

            // Fetching student details using the provided student ID
            ModuleImpl module = new ModuleImpl();
            try {
                Students foundStudent = module.findStudent(student);
                if (foundStudent != null) {
                    // Displaying student details
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Student Details</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Student Details</h1>");
                    out.println("<p>Student ID: " + foundStudent.getId() + "</p>");
                    out.println("<p>First Name: " + foundStudent.getFirstName() + "</p>");
                    out.println("<p>Last Name: " + foundStudent.getLastName() + "</p>");
                    out.println("<p>Email: " + foundStudent.getEmail() + "</p>");
                    out.println("<p>Phone Number: " + foundStudent.getPhoneNumber() + "</p>");
                    out.println("<p>Enrollment Date: " + foundStudent.getEnrollmentDate() + "</p>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    out.println("Student not found.");
                }
            } catch (Exception ex) {
                out.println("Failed to fetch student details: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for finding a student by ID";
    }
}
