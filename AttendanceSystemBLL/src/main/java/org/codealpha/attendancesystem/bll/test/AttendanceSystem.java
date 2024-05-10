/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package org.codealpha.attendancesystem.bll.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.codealpha.attendancesystem.bll.ModuleImpl;
import org.codealpha.attendancesystem.dal.HibernateDBManager;
import org.codealpha.attendancesystem.dal.entity.Students;

/**
 *
 * @author Mohamed
 */
public class AttendanceSystem {
    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public static void main(String[] args) {
        ModuleImpl module = new ModuleImpl();
        String dateString = "2024-05-06";
        Date date = convertStringToDate(dateString);
        Students student = new Students("ahmed", "ali", "ah158@gmail.com", date);
        try {
            String fileName = "org\\codealpha\\attendancesystem\\dal\\hibernate.cfg.xml";
            HibernateDBManager.setDbConfigFileName(fileName);
            HibernateDBManager.buildSessionFactory();
//            Students newStudent = module.addStudent(student);
            List<Students> studentsList = module.findAllStudents();
            for(Students stu: studentsList){
                System.out.print("Id : "+stu.getId()+" | First Name : "+stu.getFirstName()+" | Last Name : "+stu.getLastName()
                        +" | Email : "+stu.getEmail()+" | Enrollment Date : "+stu.getEnrollmentDate());
                System.out.println();
            }
//            if(newStudent != null){
//                System.out.println("Student added successfully.");
//            } else {
//                System.out.println("Student already exist.");
//            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        HibernateDBManager.getDbSessionFactory().close();
    }
}

//        try {
//            String fileName = "org//codealpha//attendancesystem//dal//hibernate.cfg.xml";
//            HibernateDBManager.setDbConfigFileName(fileName);
//            HibernateDBManager.buildSessionFactory();
//            Session commonRepo = HibernateDBManager.getCommonRepo();
//            
//            try {
//                Students newStudent = new Students("Mohamed", "Abdelghani", "test");
//                String dateString = "2024-05-06"; // Sample date string
//                Date date = convertStringToDate(dateString);
//                newStudent.setEnrollmentDate(date);
//
//            HibernateDBManager.beginTransaction();
//            Integer savedStudentId = (Integer) commonRepo.save(newStudent);
//
//            if (savedStudentId != null) {
//                System.out.println(savedStudentId);
//            }
//            HibernateDBManager.commitTransaction();
//        } catch (HibernateException dbEx) {
//            System.err.println("Database Error : " + dbEx.getMessage());
//            HibernateDBManager.rollbackTransaction();
//        }
//
//
//        } catch (Exception ex) {
//            System.err.println("ERROR : "+ex.getMessage());
//        }