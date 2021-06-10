package com.swvalerian.crud.repository.hibernate;

import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.repository.DeveloperRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateDeveloperRepositoryImpl implements DeveloperRepository {
    SessionFactory sessionFactory = HibernateSessionInit.getSessionFactory();

    @Override
    public List<Developer> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String queryStr = "FROM Developer d LEFT JOIN FETCH d.skills";
        Query<Developer> query = session.createQuery(queryStr);
        List<Developer> developerList = query.list();

        transaction.commit();
        session.close();
        return developerList;
    }

    @Override
    public Developer getId(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String queryStr = "FROM Developer d LEFT JOIN FETCH d.skills WHERE d.id =: idInt";
        Query<Developer> query = session.createQuery(queryStr);
        query.setParameter("idInt", id.intValue());
        Developer dev = query.getSingleResult();

        transaction.commit();
        session.close();
        return dev;
    }

    @Override
    public List<Developer> update(Developer dev) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(dev);

        transaction.commit();
        session.close();
        return getAll();
    }

    @Override
    public Developer save(Developer dev) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(dev);

        transaction.commit();
        session.close();
        return dev;
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Developer developer = session.load(Developer.class, id.intValue());
        session.delete(developer);

        transaction.commit();
        session.close();
    }
}
