package org.codealpha.attendancesystemwebapp.attendancerecord;

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
import org.codealpha.attendancesystem.dal.entity.AttendanceRecords;
import org.codealpha.attendancesystem.dal.entity.StudentsCources;

public class AddAttendanceRecordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add Attendance Record</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Add Attendance Record</h1>");
            out.println("<form method=\"post\">");
            out.println("StudentsCources ID: <input type=\"text\" name=\"studentsCourcesId\" required><br>");
            out.println("Attendance Date: <input type=\"date\" name=\"attendanceDate\" required><br>");
            out.println("Status: <input type=\"text\" name=\"status\" required><br>");
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
            String attendanceDateString = request.getParameter("attendanceDate");
            String status = request.getParameter("status");
            StudentsCources studentsCourse = new StudentsCources();

            // Parsing attendance date
            Date attendanceDate = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                attendanceDate = dateFormat.parse(attendanceDateString);
            } catch (ParseException e) {
                out.println("Failed to parse attendance date: " + e.getMessage());
                return;
            }

            // Creating a new AttendanceRecords object
            AttendanceRecords attendanceRecord = new AttendanceRecords();
            attendanceRecord.setStudentsCources(studentsCourse);
            attendanceRecord.setAttendanceDate(attendanceDate);
            attendanceRecord.setStatus(status);

            // Adding the attendance record using ModuleImpl
            ModuleImpl module = new ModuleImpl();
            try {
                AttendanceRecords newAttendanceRecord = module.addAttendanceRecord(attendanceRecord);
                out.println("Attendance record added successfully with ID: " + newAttendanceRecord.getId());
            } catch (Exception ex) {
                out.println("Failed to add attendance record: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for adding a new attendance record";
    }
}
