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

public class EditAttendanceRecordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Attendance Record</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edit Attendance Record</h1>");
            out.println("<form method=\"post\">");
            out.println("Attendance Record ID: <input type=\"text\" name=\"attendanceRecordId\" required><br>");
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
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            int attendanceRecordId = Integer.parseInt(request.getParameter("attendanceRecordId"));
            String attendanceDateString = request.getParameter("attendanceDate");
            String status = request.getParameter("status");

            Date attendanceDate = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                attendanceDate = dateFormat.parse(attendanceDateString);
            } catch (ParseException e) {
                out.println("Failed to parse attendance date: " + e.getMessage());
                return;
            }

            AttendanceRecords attendanceRecord = new AttendanceRecords();
            attendanceRecord.setId(attendanceRecordId);
            attendanceRecord.setAttendanceDate(attendanceDate);
            attendanceRecord.setStatus(status);

            ModuleImpl module = new ModuleImpl();
            try {
                AttendanceRecords updatedRecord = module.editAttendanceRecord(attendanceRecord);
                out.println("Attendance record updated successfully");
            } catch (Exception ex) {
                out.println("Failed to update attendance record: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for editing an attendance record";
    }
}
