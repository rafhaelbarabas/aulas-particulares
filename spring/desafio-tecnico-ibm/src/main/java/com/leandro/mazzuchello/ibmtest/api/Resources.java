package com.leandro.mazzuchello.ibmtest.api;

import com.leandro.mazzuchello.ibmtest.entities.People;
import com.leandro.mazzuchello.ibmtest.entities.Simulation;
import com.leandro.mazzuchello.ibmtest.repositories.PeopleRepository;
import com.leandro.mazzuchello.ibmtest.repositories.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class Resources {

    private final PeopleRepository peopleRepository;

    private final SimulationRepository simulationRepository;

    @Autowired
    public Resources(PeopleRepository peopleRepository, SimulationRepository simulationRepository) {
        this.peopleRepository = peopleRepository;
        this.simulationRepository = simulationRepository;
    }

    // Criar uma nova pessoa
    @PostMapping("/create-people")
    public ResponseEntity<People> createPeople(@RequestBody @Valid People people) {
        People savedPeople = peopleRepository.saveAndFlush(people);
        return ResponseEntity.ok(savedPeople);
    }

    // Listar todas as pessoas
    @GetMapping("/peoples")
    public ResponseEntity<List<People>> getPeoples() {
        return ResponseEntity.ok(peopleRepository.findAll());
    }

    // Deletar uma pessoa por ID
    @DeleteMapping("/delete-people/{peopleId}")
    public ResponseEntity<Void> deletePeople(@PathVariable("peopleId") Long peopleId) {
        try {
            peopleRepository.deleteById(peopleId);
        } catch (EmptyResultDataAccessException exp) {
            throw new EntityNotFoundException(String.format("Pessoa com id %s não encontrada", peopleId));
        }
        return ResponseEntity.noContent().build();
    }

    // Cadastrar uma simulação
    @PostMapping("/create-simulation")
    public ResponseEntity<Simulation> createSimulation(@RequestBody @Valid Simulation simulation) {
        Simulation savedSimulation = simulationRepository.saveAndFlush(simulation);
        return ResponseEntity.ok(savedSimulation);
    }

    // Listar todas as simulações cadastradas
    @GetMapping("/simulations")
    public ResponseEntity<List<Simulation>> getSimulations() {
        return ResponseEntity.ok(simulationRepository.findAll());
    }

    // consultar restrição por um CPF
    @GetMapping("/get-restrictions/{cpf}")
    public ResponseEntity<Boolean> getRestrictions(@PathVariable("cpf") String cpf) {
        Optional<People> peopleByCpf = Optional.of(peopleRepository.findPeopleByCpf(cpf));
        if (peopleByCpf.isPresent()) {
            People people = peopleByCpf.get();
            return ResponseEntity.ok(people.getHasRestriction());
        } else {
            throw new EntityNotFoundException(String.format("Pessoa com CPF %s não encontrada", cpf));
        }
    }

    // consultar uma simulação pelo CPF
    @GetMapping("/simulation-by-cpf/{cpf}")
    public ResponseEntity<List<Simulation>> getSimulationByCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(simulationRepository.findAllByPeopleCpf(cpf));
    }

    // remover uma simulação
    @DeleteMapping("/delete-simulation/{id}")
    public ResponseEntity<Void> deleteSimulation(@PathVariable("id") Long id) {
        try {
            simulationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exp) {
            throw new EntityNotFoundException(String.format("Simulação com id %s não encontrada", id));
        }
        return ResponseEntity.noContent().build();
    }

    //* alterar uma simulação
    @PutMapping("/update-simulation/{id}")
    public ResponseEntity<Simulation> updateSimulation(@PathVariable("id") Long id,
                                                       @RequestBody Simulation updateBody) {
        Optional<Simulation> simulation = simulationRepository.findById(id);
        if (simulation.isPresent()) {
            Simulation updatedSimulation = simulation.get();
            updatedSimulation.setPeople(updateBody.getPeople());
            simulationRepository.save(updatedSimulation);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException(String.format("Simulação id %s não encontrada", id));
        }
    }
}
