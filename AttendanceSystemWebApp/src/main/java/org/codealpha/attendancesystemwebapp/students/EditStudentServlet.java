package org.codealpha.attendancesystemwebapp.students;

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
import org.codealpha.attendancesystem.dal.entity.Students;

public class EditStudentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Student</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edit Student</h1>");
            out.println("<form method=\"post\">");
            out.println("Student ID: <input type=\"text\" name=\"studentId\" required><br>");
            out.println("First Name: <input type=\"text\" name=\"firstName\" required><br>");
            out.println("Last Name: <input type=\"text\" name=\"lastName\" required><br>");
            out.println("Email: <input type=\"text\" name=\"email\" required><br>");
            out.println("Phone Number: <input type=\"text\" name=\"phoneNumber\"><br>");
            out.println("Enrollment Date: <input type=\"date\" name=\"enrollmentDate\" required><br>");
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
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String enrollmentDateString = request.getParameter("enrollmentDate");

            Date enrollmentDate = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                enrollmentDate = dateFormat.parse(enrollmentDateString);
            } catch (ParseException e) {
                out.println("Failed to parse enrollment date: " + e.getMessage());
                return;
            }

            Students student = new Students();
            student.setId(studentId);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setPhoneNumber(phoneNumber);
            student.setEnrollmentDate(enrollmentDate);

            ModuleImpl module = new ModuleImpl();
            try {
                Students updatedStudent = module.editStudent(student);
                out.println("Student updated successfully");
            } catch (Exception ex) {
                out.println("Failed to update student: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for editing a student";
    }
}
