/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.codealpha.attendancesystem.bll;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codealpha.attendancesystem.dal.HibernateDBManager;
import static org.codealpha.attendancesystem.dal.HibernateDBManager.*;
import org.codealpha.attendancesystem.dal.entity.*;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mohamed
 */
public class ModuleImpl implements Module{
    
    public ModuleImpl(){
        String fileName = "org\\codealpha\\attendancesystem\\dal\\hibernate.cfg.xml";
        
        try {
            HibernateDBManager.setDbConfigFileName(fileName);
            HibernateDBManager.buildSessionFactory();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Students addStudent(Students student) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            Students newStudent = (Students)commonRepo.merge(student);
            commitTransaction();
            return newStudent;
        } catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public Students editStudent(Students student) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.update(student);
            
            commitTransaction();
            
            return student;
        }catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public void deleteStudent(Students student) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.delete(student);
            
            commitTransaction();
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<Students> findAllStudents() throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Criteria criteria = commonRepo
                    .createCriteria(Students.class)
                    .addOrder(Order.asc("firstName"))
                    .addOrder(Order.asc("lastName"));
            List<Students> studentsList = criteria.list();
            
            commitTransaction();
            
            return studentsList;
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public Students findStudent(Students student) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Students foundStudent = commonRepo.load(Students.class, student.getId());
            
            commitTransaction();
            
            return foundStudent;
        }catch (Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }
    
    @Override
    public List<Students> searchStudentsByName(String studentName) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Criteria criteria = commonRepo
                    .createCriteria(Students.class)
                    .add(Restrictions.like("firstName", studentName, MatchMode.ANYWHERE))
                    .addOrder(Order.asc("firstName"))
                    .addOrder(Order.asc("lastName"));
            List<Students> studentsList = criteria.list();
            
            commitTransaction();
            
            return studentsList;
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public Cources addCourse(Cources course) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Cources newCourse = (Cources)commonRepo.merge(course);
            
            commitTransaction();
            
            return newCourse;
        } catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public Cources editCourse(Cources course) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.update(course);
            
            commitTransaction();
            
            return course;
        }catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public void deleteCourse(Cources course) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.delete(course);
            
            commitTransaction();
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<Cources> findAllCourses() throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Criteria criteria = commonRepo
                    .createCriteria(Cources.class)
                    .addOrder(Order.asc("courseName"));
            List<Cources> coursesList = criteria.list();
            
            commitTransaction();
            
            return coursesList;
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public Cources findCourse(Cources course) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Cources foundCourse = commonRepo.load(Cources.class, course.getId());
            
            commitTransaction();
            
            return foundCourse;
        }catch (Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }
    
    @Override
    public List<Cources> searchCoursesByName(String courseName) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Criteria criteria = commonRepo
                    .createCriteria(Cources.class)
                    .add(Restrictions.like("courseName", courseName, MatchMode.ANYWHERE))
                    .addOrder(Order.asc("courseName"));
            List<Cources> coursesList = criteria.list();
            
            commitTransaction();
            
            return coursesList;
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public StudentsCources addStudentsCourse(StudentsCources studentCourse) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            StudentsCources newStudentsCourse = (StudentsCources)commonRepo.merge(studentCourse);
            commitTransaction();
            return newStudentsCourse;
        } catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public StudentsCources editStudentsCourse(StudentsCources studentCourse) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.update(studentCourse);
            
            commitTransaction();
            
            return studentCourse;
        }catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public void deleteStudentsCourse(StudentsCources studentsCourse) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.delete(studentsCourse);
            
            commitTransaction();
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<StudentsCources> findAllStudentsCourses() throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Criteria criteria = commonRepo
                    .createCriteria(StudentsCources.class);
            List<StudentsCources> studentsCourcesList = criteria.list();
            
            commitTransaction();
            
            return studentsCourcesList;
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public StudentsCources findStudentsCourse(StudentsCources studentCourse) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            StudentsCources foundStudentCourse = commonRepo.load(StudentsCources.class, studentCourse.getId());
            
            commitTransaction();
            
            return foundStudentCourse;
        }catch (Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public AttendanceRecords addAttendanceRecord(AttendanceRecords attendanceRecord) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            AttendanceRecords newAttendanceRecord = (AttendanceRecords)commonRepo.merge(attendanceRecord);
            commitTransaction();
            return newAttendanceRecord;
        } catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public AttendanceRecords editAttendanceRecord(AttendanceRecords attendanceRecord) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.update(attendanceRecord);
            
            commitTransaction();
            
            return attendanceRecord;
        }catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public void deleteAttendanceRecord(AttendanceRecords attendanceRecord) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.delete(attendanceRecord);
            
            commitTransaction();
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<AttendanceRecords> findAllAttendanceRecords() throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Criteria criteria = commonRepo
                    .createCriteria(AttendanceRecords.class);
            List<AttendanceRecords> attendanceRecordsList = criteria.list();
            
            commitTransaction();
            
            return attendanceRecordsList;
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public AttendanceRecords findAttendanceRecord(AttendanceRecords attendanceRecord) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            AttendanceRecords foundAttendanceRecord = commonRepo.load(AttendanceRecords.class, attendanceRecord.getId());
            
            commitTransaction();
            
            return foundAttendanceRecord;
        }catch (Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public Assignments addAssignment(Assignments assignment) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            Assignments newAssignment = (Assignments)commonRepo.merge(assignment);
            commitTransaction();
            return newAssignment;
        } catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public Assignments editAssignment(Assignments assignment) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.update(assignment);
            
            commitTransaction();
            
            return assignment;
        }catch(Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public void deleteAssignment(Assignments assignment) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            commonRepo.delete(assignment);
            
            commitTransaction();
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<Assignments> findAllAssignments() throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Criteria criteria = commonRepo
                    .createCriteria(Assignments.class)
                    .addOrder(Order.asc("title"));
            List<Assignments> assignmentsList = criteria.list();
            
            commitTransaction();
            
            return assignmentsList;
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public Assignments findAssignment(Assignments assignment) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Assignments foundAssignment = commonRepo.load(Assignments.class, assignment.getId());
            
            commitTransaction();
            
            return foundAssignment;
        }catch (Exception ex){
            rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<Assignments> searchAssignmentsByTitle(String title) throws Exception {
        try{
            Session commonRepo = getCommonRepo();
            beginTransaction();
            
            Criteria criteria = commonRepo
                    .createCriteria(Assignments.class)
                    .add(Restrictions.like("title", title, MatchMode.ANYWHERE))
                    .addOrder(Order.asc("title"));
            List<Assignments> assignmentsList = criteria.list();
            
            commitTransaction();
            
            return assignmentsList;
        }catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }
    
}
