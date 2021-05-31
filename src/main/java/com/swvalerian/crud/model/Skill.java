package com.swvalerian.crud.model;

import javax.persistence.*;

@Entity
@Table(name = "Skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "skill")
    private String name;

    public Skill() {
    }

    public Skill(Integer id, String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "\n" + "Skill {" +
                "id=" + id +
                " name='" + name + "'" +
                "}";
    }
}
