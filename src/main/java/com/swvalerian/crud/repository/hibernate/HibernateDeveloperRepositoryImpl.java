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

        String queryStr = "FROM Developer dev LEFT JOIN FETCH dev.skills";
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
    } //        return getAll().stream().filter(dev -> dev.getId().equals(id.intValue())).findFirst().orElse(null);

    @Override
    public List<Developer> update(Developer developer) {
        return null;
    }

    @Override
    public Developer save(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(developer);

        transaction.commit();
        session.close();
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Developer developer = session.get(Developer.class, id.intValue());
        session.delete(developer);

        transaction.commit();
        session.close();
    }
}
