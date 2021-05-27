package com.swvalerian.crud.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.swvalerian.crud.model.Skill;
import com.swvalerian.crud.repository.SkillRepo;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HibernateSkillRepositoryImpl implements SkillRepo {
    private static SessionFactory sessionFactory;

    @Override
    public List<Skill> getAll() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // в запросе указываем класс, работаем с обьектами классов
        Query<Skill> query = session.createQuery("FROM Skill");

        List<Skill> skillList = query.list();

        transaction.commit();
        session.close();

        return skillList;
    }

    @Override
    public Skill getId(Integer id) {
        return getAll().stream().filter(skill -> skill.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Skill> update(Skill skill) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(skill);

        transaction.commit();
        session.close();

        return getAll();
    }

    @Override
    public Skill save(Skill skill) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(skill);

        transaction.commit();
        session.close();

        return skill;
    }

    @Override
    public void deleteById(Integer id) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Skill skill = session.get(Skill.class, id);
        session.delete(skill);

        transaction.commit();
        session.close();
    }
}
