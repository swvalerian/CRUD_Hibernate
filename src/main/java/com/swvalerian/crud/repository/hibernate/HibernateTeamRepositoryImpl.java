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

//        String queryStr = "FROM Team"; //  t LEFT JOIN FETCH t.Developers
//        Query<Team> query = session.createQuery("FROM Team t LEFT JOIN t.developers");
//        List<Team> teamList = session.createQuery("from Team").list();
        Query<Team> query = session.createQuery("FROM Team t " +
                "LEFT JOIN FETCH t.developers");
        List<Team> teamList = query.list();

        transaction.commit();
        session.close();
        return teamList;
    }

    @Override
    public Team getId(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//Team team = new Team();

        String queryStr = "SELECT t " +
                    "FROM Team t " +
                    "LEFT JOIN FETCH t.developers " +
                    "WHERE t.id =: idInt";

        Query<Team> query = session.createQuery(queryStr);
        query.setParameter("idInt", aLong.intValue());
        Team team = query.getSingleResult();

        transaction.commit();
        session.close();
        return team;
    }

   /* @Override
    public Team getId(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String queryStr = "SELECT t FROM Team t LEFT JOIN FETCH t.developers.skills WHERE t.id =: idInt";

        Query<Team> query = session.createQuery(queryStr);
        query.setParameter("idInt", aLong.intValue());
        Team team = query.getSingleResult();

        transaction.commit();
        session.close();
        return team;
    }

    @Override
    public List<Team> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        String queryStr = "FROM Team"; //  t LEFT JOIN FETCH t.Developers
//        Query<Team> query = session.createQuery("FROM Team t LEFT JOIN t.developers");
//        List<Team> teamList = session.createQuery("from Team").list();
        Query<Team> query = session.createQuery("Select DISTINCT t.name FROM Team t LEFT JOIN FETCH t.developers.skills");
        List<Team> teamList = query.list();

        transaction.commit();
        session.close();
        return teamList;
    }*/


    @Override
    public List<Team> update(Team team) {
        return null;
    }

    @Override
    public Team save(Team team) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
