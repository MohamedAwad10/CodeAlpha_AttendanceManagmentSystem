/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.codealpha.attendancesystem.dal;

import org.codealpha.attendancesystem.dal.entity.Assignments;
import org.codealpha.attendancesystem.dal.entity.AttendanceRecords;
import org.codealpha.attendancesystem.dal.entity.Cources;
import org.codealpha.attendancesystem.dal.entity.Students;
import org.codealpha.attendancesystem.dal.entity.StudentsCources;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Mohamed
 */
public class HibernateDBManager {
    private static SessionFactory dbSessionFactory;
    private static String dbConfigFileName;

    public static void setDbConfigFileName(String dbConfigFileName) {
        HibernateDBManager.dbConfigFileName = dbConfigFileName;
    }

    public static void buildSessionFactory() throws Exception {
        try {
            if (dbSessionFactory != null) {
                return;
            }

            if (dbConfigFileName == null) {
                throw new Exception("ERROR : You MUST call setDbConfigFileName(filename) first.");
            }

            // Loading configuration from xml file (Database Connection Info)
            Configuration configuration = new Configuration().configure(dbConfigFileName)
                    .addAnnotatedClass(Students.class)
                    .addAnnotatedClass(Cources.class)
                    .addAnnotatedClass(Assignments.class)
                    .addAnnotatedClass(AttendanceRecords.class)
                    .addAnnotatedClass(StudentsCources.class);

            // Prepare the serviceRegistryBuilder
            StandardServiceRegistryBuilder serviceRegisterBuilder = new StandardServiceRegistryBuilder();

            // applySettings (Database Connection Info) to the builder
            serviceRegisterBuilder.applySettings(configuration.getProperties());

            // obtain the serviceRegistry (Legal Paper)
            StandardServiceRegistry serviceRegistry = serviceRegisterBuilder.build();

            // building the sessionFactory(الكشك)
            dbSessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (HibernateException ex) {
            dbSessionFactory = null;
            dbConfigFileName = null;
            throw ex;
        }
    }

    public static SessionFactory getDbSessionFactory() {
        return dbSessionFactory;
    }
    
    public static Session getCommonRepo() {
        return dbSessionFactory.getCurrentSession();
    }
    
    public static void beginTransaction() {
        dbSessionFactory.getCurrentSession().beginTransaction();
    }
    
    public static void commitTransaction() {
        dbSessionFactory.getCurrentSession().getTransaction().commit();
    }
    
    public static void rollbackTransaction() {
        dbSessionFactory.getCurrentSession().getTransaction().rollback();
    }
}
