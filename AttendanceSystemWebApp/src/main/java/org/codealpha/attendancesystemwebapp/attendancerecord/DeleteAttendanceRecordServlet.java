package org.codealpha.attendancesystemwebapp.attendancerecord;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.AttendanceRecords;

public class DeleteAttendanceRecordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Attendance Record</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Delete Attendance Record</h1>");
            out.println("<form method=\"post\">");
            out.println("Attendance Record ID: <input type=\"text\" name=\"attendanceRecordId\" required><br><br>");
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
            int attendanceRecordId = Integer.parseInt(request.getParameter("attendanceRecordId"));

            ModuleImpl module = new ModuleImpl();
            AttendanceRecords attendanceRecord = new AttendanceRecords();
            attendanceRecord.setId(attendanceRecordId);

            try {
                attendanceRecord = module.findAttendanceRecord(attendanceRecord);
                if (attendanceRecord == null) {
                    out.println("Attendance record not found with ID: " + attendanceRecordId);
                    return;
                }
            } catch (Exception ex) {
                out.println("Failed to retrieve attendance record details: " + ex.getMessage());
                return;
            }

            try {
                module.deleteAttendanceRecord(attendanceRecord);
                out.println("Attendance record deleted successfully");
            } catch (Exception ex) {
                out.println("Failed to delete attendance record: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for deleting an attendance record";
    }
}
