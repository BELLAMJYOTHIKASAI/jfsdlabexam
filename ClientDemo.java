package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {
    public static void main(String[] args) {
        // Create Configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Department.class);

        // Create SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Insert operation
        insertDepartment(sessionFactory);

        // Delete operation
        deleteDepartmentById(sessionFactory, 1);

        // Close the session factory
        sessionFactory.close();
    }

    // Insert a department
    public static void insertDepartment(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Department department = new Department();
        department.setName("Computer Science");
        department.setLocation("Block A");
        department.setHodName("Dr. Smith");

        session.persist(department);
        transaction.commit();

        System.out.println("Department inserted successfully!");
        session.close();
    }

    // Delete a department by ID
    public static void deleteDepartmentById(SessionFactory sessionFactory, int departmentId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM Department WHERE departmentId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", departmentId);

        int result = query.executeUpdate();
        if (result > 0) {
            System.out.println("Department with ID " + departmentId + " deleted successfully!");
        } else {
            System.out.println("No Department found with ID " + departmentId);
        }

        transaction.commit();
        session.close();
    }
}
