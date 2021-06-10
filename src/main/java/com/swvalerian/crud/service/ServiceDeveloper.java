package com.swvalerian.crud.service;

import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.repository.hibernate.HibernateDeveloperRepositoryImpl;
import com.swvalerian.crud.repository.jdbc.JavaIODevRepImpl;
import com.swvalerian.crud.repository.jdbc.SkillRepository;

import java.util.List;

public class ServiceDeveloper  {

    final private HibernateDeveloperRepositoryImpl devRep = new HibernateDeveloperRepositoryImpl();

    public Developer create(Integer id, String firstName, String lastName) {
        devRep.save(new Developer(id, firstName, lastName, new SkillRepository().getAll()));
        return new Developer(id, firstName, lastName, new SkillRepository().getAll());
    }

    public Developer read(Long id) {
        System.out.println(devRep.getId(id.longValue()));
        return devRep.getId(id.longValue());
    }

    public Developer update(Integer id, String firstName, String lastName) {
        devRep.update(new Developer(id, firstName, lastName, new SkillRepository().getAll()));
        return new Developer(id, firstName, lastName, new SkillRepository().getAll());
    }

    public void delete(Long id) {
        devRep.deleteById(id);
    }

    public List<Developer> getAll() {
        return devRep.getAll();
    }
}
