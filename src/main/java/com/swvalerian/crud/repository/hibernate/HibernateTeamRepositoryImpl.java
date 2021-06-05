package com.swvalerian.crud.repository.hibernate;

import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.model.Team;
import com.swvalerian.crud.repository.TeamRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateTeamRepositoryImpl implements TeamRepository {
    SessionFactory sessionFactory = HibernateSessionInit.getSessionFactory();

    @Override
    public List<Team> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Team> query = session.createQuery("FROM Team t LEFT JOIN FETCH t.developers");
        List<Team> teamList = query.list();

        transaction.commit();
        session.close();
        return teamList;
    }

    @Override
    public Team getId(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String queryStr = "SELECT t " +
                    "FROM Team t " +
                    "LEFT JOIN FETCH t.developers " +
                    "WHERE t.id =: idInt";

        Query<Team> query = session.createQuery(queryStr);
        query.setParameter("idInt", id.intValue());
        Team team = query.getSingleResult();

        transaction.commit();
        session.close();
        return team;
    }

    @Override
    public List<Team> update(Team team) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(team);

        transaction.commit();
        session.close();
        return getAll();
    }

    @Override
    public Team save(Team team) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(team);

        transaction.commit();
        session.close();
        return team;
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Team team = session.load(Team.class, id.intValue());
        session.delete(team);

        transaction.commit();
        session.close();
    }
}
