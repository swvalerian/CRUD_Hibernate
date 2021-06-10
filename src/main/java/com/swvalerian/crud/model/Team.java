package com.swvalerian.crud.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "Team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    Integer id;

    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "Team_Developers",
                joinColumns =@JoinColumn(name = "Team_Id", referencedColumnName = "Id"),
                inverseJoinColumns = @JoinColumn(name = "Dev_Id", referencedColumnName = "Id"))
    List<Developer> developers;

    public Team() {
    }

    @Override
    public String toString() {
        return "\n Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developers=" + developers +
                '}' + "\n";
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

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public Team(Integer id, String name, List<Developer> developers) {
        this.id = id;
        this.name = name;
        this.developers = developers;
    }
}
