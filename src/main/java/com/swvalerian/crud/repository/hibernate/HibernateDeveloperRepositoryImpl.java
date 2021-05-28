package com.swvalerian.crud.repository.hibernate;

import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.model.Skill;
import com.swvalerian.crud.repository.DeveloperRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateDeveloperRepositoryImpl implements DeveloperRepository {
    private static SessionFactory sessionFactory;

    @Override
    public List<Developer> getAll() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // в запросе указываем класс, работаем с обьектами классов
        Query<Developer> query = session.createQuery("FROM Developer");
        List<Developer> developerList = query.list();

        transaction.commit();
        session.close();
        return developerList;
    }

    @Override
    public Developer getId(Long id) {
        return getAll().stream().filter(dev -> dev.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Developer> update(Developer developer) {
        return null;
    }

    @Override
    public Developer save(Developer developer) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
