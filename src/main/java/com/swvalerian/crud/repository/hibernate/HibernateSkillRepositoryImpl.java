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
        Transaction transaction = null;

        transaction = session.beginTransaction();

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

       /* sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        // в запросе указываем класс, работаем с обьектами классов
        Skill getSkill = (Skill) session.get(Skill.class, id);

        transaction.commit();
        session.close();

        return getSkill;*/
    }

    @Override
    public List<Skill> update(Skill skill) {
        return null;
    }

    @Override
    public Skill save(Skill skill) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
