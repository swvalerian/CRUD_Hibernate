package com.swvalerian.crud.service;

import com.swvalerian.crud.model.Team;
import com.swvalerian.crud.repository.hibernate.HibernateDeveloperRepositoryImpl;
import com.swvalerian.crud.repository.hibernate.HibernateTeamRepositoryImpl;
import com.swvalerian.crud.repository.jdbc.JavaIODevRepImpl;
import com.swvalerian.crud.repository.jdbc.JavaIOTeamRepImpl;

import java.util.List;

public class ServiceTeam {
    final private HibernateTeamRepositoryImpl teamCon = new HibernateTeamRepositoryImpl();

    public Team create(Integer id, String name) {
        Team team = new Team();
        team.setId(id);
        team.setName(name);

        teamCon.save(team);
        return team;
    }

    public Team read(Integer id) {
        return teamCon.getId(id.longValue());
    }

    public Team update(Integer id, String name) {
        Team team = new Team();
        team.setId(id);
        team.setName(name);

        teamCon.update(team);
        return team;
    }

    public void delete(Integer id) {
        teamCon.deleteById(id.longValue());
    }

    public List<Team> getAll() {
        return teamCon.getAll();
    }
}
