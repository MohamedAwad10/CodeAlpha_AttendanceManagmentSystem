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

public class AddStudentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add Student</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Add Student</h1>");
            out.println("<form method=\"post\">");
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Extracting parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String enrollmentDateString = request.getParameter("enrollmentDate");

            // Parsing enrollment date
            Date enrollmentDate = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                enrollmentDate = dateFormat.parse(enrollmentDateString);
            } catch (ParseException e) {
                out.println("Failed to parse enrollment date: " + e.getMessage());
                return;
            }

            // Creating a new Students object
            Students student = new Students(firstName, lastName, email, enrollmentDate);
            student.setPhoneNumber(phoneNumber);

            // Adding the student using ModuleImpl
            ModuleImpl module = new ModuleImpl();
            try {
                Students newStudent = module.addStudent(student);
                out.println("Student added successfully with ID: " + newStudent.getId());
            } catch (Exception ex) {
                out.println("Failed to add student: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for adding a new student";
    }
}
