package com.swvalerian.crud.service;

import com.swvalerian.crud.controller.ControllerIF;
import com.swvalerian.crud.model.Skill;
import com.swvalerian.crud.repository.hibernate.HibernateSkillRepositoryImpl;
import com.swvalerian.crud.repository.jdbc.SkillRepository;

import java.util.List;

public class ServiceSkill implements ControllerIF {
    // измениться эта строка, здесь мы будем работать через HIBERNATE
    // и будет выглядеть так HibernateSkillRepositoryImpl HSR = new HibernateSkillRepositoryImpl();
    final private HibernateSkillRepositoryImpl skillRepository = new HibernateSkillRepositoryImpl();

    @Override
    public Skill create(Integer id, String name) {
        skillRepository.save(new Skill(id, name));
        return new Skill(id, name);
    }

    @Override
    public Skill read(Integer id) {
        return skillRepository.getId(id);
    }

    @Override
    public Skill update(Integer id, String name) {
        skillRepository.update(new Skill(id, name));
        return new Skill(id, name);
    }

    @Override
    public void delete(Integer id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> getAll() {
        return skillRepository.getAll();
    }
}
