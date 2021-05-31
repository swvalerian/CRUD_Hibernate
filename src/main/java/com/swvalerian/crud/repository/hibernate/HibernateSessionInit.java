package com.swvalerian.crud.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionInit {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        return sessionFactory;
    }
}
