package com.leandro.mazzuchello.ibmtest.entities;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CPF
    private String cpf;

    private Boolean hasRestriction = Boolean.FALSE;

    @OneToMany(mappedBy = "people")
    private List<Simulation> simulations = new ArrayList<>();

    public People() {
    }

    public People(String name, String cpf, Boolean hasRestriction) {
        this.name = name;
        this.cpf = cpf;
        this.hasRestriction = hasRestriction;
    }

    public People(Long id, String name, String cpf, Boolean hasRestriction) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.hasRestriction = hasRestriction;
    }

    public void addSimulation(Simulation simulation) {
        simulations.add(simulation);
    }

    public void removeSimulation(Simulation simulation) {
        simulations.remove(simulation);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getHasRestriction() {
        return hasRestriction;
    }

    public void setHasRestriction(Boolean hasRestriction) {
        this.hasRestriction = hasRestriction;
    }
}
