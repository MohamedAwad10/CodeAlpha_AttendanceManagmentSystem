/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.codealpha.attendancesystem.bll;

import java.util.List;
import org.codealpha.attendancesystem.dal.entity.*;

/**
 *
 * @author Mohamed
 */
public interface Module {
    
    Students addStudent(Students student) throws Exception;
    Students editStudent(Students student) throws Exception;
    void deleteStudent(Students student) throws Exception;
    List<Students> findAllStudents() throws Exception;
    Students findStudent(Students student) throws Exception;
    List<Students> searchStudentsByName(String studentName) throws Exception;
    
    Cources addCourse(Cources course) throws Exception;
    Cources editCourse(Cources course) throws Exception;
    void deleteCourse(Cources course) throws Exception;
    List<Cources> findAllCourses() throws Exception;
    Cources findCourse(Cources course) throws Exception;
    List<Cources> searchCoursesByName(String courseName) throws Exception;
    
    StudentsCources addStudentsCourse(StudentsCources studentCourse) throws Exception;
    StudentsCources editStudentsCourse(StudentsCources studentCourse) throws Exception;
    void deleteStudentsCourse(StudentsCources studentsCourse) throws Exception;
    List<StudentsCources> findAllStudentsCourses() throws Exception;
    StudentsCources findStudentsCourse(StudentsCources studentCourse) throws Exception;
    
    AttendanceRecords addAttendanceRecord(AttendanceRecords attendanceRecord) throws Exception;
    AttendanceRecords editAttendanceRecord(AttendanceRecords attendanceRecord) throws Exception;
    void deleteAttendanceRecord(AttendanceRecords attendanceRecord) throws Exception;
    List<AttendanceRecords> findAllAttendanceRecords() throws Exception;
    AttendanceRecords findAttendanceRecord(AttendanceRecords attendanceRecord) throws Exception;
    
    Assignments addAssignment(Assignments assignment) throws Exception;
    Assignments editAssignment(Assignments assignment) throws Exception;
    void deleteAssignment(Assignments assignment) throws Exception;
    List<Assignments> findAllAssignments() throws Exception;
    Assignments findAssignment(Assignments assignment) throws Exception;
    List<Assignments> searchAssignmentsByTitle(String assignmentName) throws Exception;
}
