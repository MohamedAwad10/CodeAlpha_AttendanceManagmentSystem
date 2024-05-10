package org.codealpha.attendancesystemwebapp.attendancerecord;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.AttendanceRecords;

public class FindAttendanceRecordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Find Attendance Record</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Find Attendance Record</h1>");
            out.println("<form method=\"post\">");
            out.println("Attendance Record ID: <input type=\"text\" name=\"attendanceRecordId\" required><br>");
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
            // Extracting attendance record ID from request parameters
            int attendanceRecordId = Integer.parseInt(request.getParameter("attendanceRecordId"));
            AttendanceRecords attendanceRecord = new AttendanceRecords();
            attendanceRecord.setId(attendanceRecordId);

            // Fetching attendance record details using the provided ID
            ModuleImpl module = new ModuleImpl();
            try {
                AttendanceRecords foundRecord = module.findAttendanceRecord(attendanceRecord);
                if (foundRecord != null) {
                    // Displaying attendance record details
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Attendance Record Details</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Attendance Record Details</h1>");
                    out.println("<p>Attendance Record ID: " + foundRecord.getId() + "</p>");
                    out.println("<p>StudentsCources ID: " + foundRecord.getStudentsCources() + "</p>");
                    out.println("<p>Attendance Date: " + foundRecord.getAttendanceDate() + "</p>");
                    out.println("<p>Status: " + foundRecord.getStatus() + "</p>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    out.println("Attendance record not found.");
                }
            } catch (Exception ex) {
                out.println("Failed to fetch attendance record details: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for finding an attendance record by ID";
    }
}
