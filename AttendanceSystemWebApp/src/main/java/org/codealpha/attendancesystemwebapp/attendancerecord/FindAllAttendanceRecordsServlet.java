package org.codealpha.attendancesystemwebapp.attendancerecord;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.entity.AttendanceRecords;

public class FindAllAttendanceRecordsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModuleImpl module = new ModuleImpl();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>All Attendance Records</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>All Attendance Records</h1>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Students Cources ID</th>");
        out.println("<th>Attendance Date</th>");
        out.println("<th>Status</th>");
        out.println("</tr>");
        try {
            List<AttendanceRecords> records = module.findAllAttendanceRecords();
            for (AttendanceRecords record : records) {
                out.println("<tr>");
                out.println("<td>" + record.getId() + "</td>");
                out.println("<td>" + record.getStudentsCources().getId() + "</td>");
                out.println("<td>" + record.getAttendanceDate() + "</td>");
                out.println("<td>" + record.getStatus() + "</td>");
                out.println("</tr>");
            }
        } catch (Exception ex) {
            out.println("<tr><td colspan=\"4\">Failed to retrieve attendance records: " + ex.getMessage() + "</td></tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
