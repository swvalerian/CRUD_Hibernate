package com.swvalerian.crud.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "Developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "firstName")
    String firstName;
    @Column(name = "lastName")
    String lastName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "Developers_Skills",
            joinColumns =@JoinColumn(name = "Dev_Skill_Id", referencedColumnName="ID")
            ,inverseJoinColumns = @JoinColumn(name = "Skill_Id", referencedColumnName="ID"))
    List<Skill> skills;

    public Developer() {
    }

    public Developer(Integer id, String firstName, String lastName, List<Skill> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    @Override

    public String toString() {
        return  "\n" + "Developer {" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", skills: " + skills +
                '}' + "\n";
    }

}
