package org.codealpha.attendancesystemwebapp.students;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.Students;

public class DeleteStudentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Student</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Delete Student</h1>");
            out.println("<form method=\"post\">");
            out.println("Student ID: <input type=\"text\" name=\"studentId\" required><br><br>");// Adding email field
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
            int studentId = Integer.parseInt(request.getParameter("studentId"));

            ModuleImpl module = new ModuleImpl();
            Students student = new Students();
            student.setId(studentId);
            student.setEmail(student.getEmail());
            student.setEnrollmentDate(student.getEnrollmentDate());
            try {
                student = module.findStudent(student);
                if (student == null) {
                    out.println("Student not found with ID: " + studentId);
                    return;
                }
            } catch (Exception ex) {
                out.println("Failed to retrieve student details: " + ex.getMessage());
                return;
            }

            
            try {
                module.deleteStudent(student);
                out.println("Student deleted successfully");
            } catch (Exception ex) {
                out.println("Failed to delete student: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for deleting a student";
    }
}
