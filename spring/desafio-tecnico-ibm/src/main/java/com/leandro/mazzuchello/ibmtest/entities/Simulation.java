package com.leandro.mazzuchello.ibmtest.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private People people;

    public Simulation() {
    }

    public Simulation(Long id, People people) {
        this.id = id;
        this.people = people;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
