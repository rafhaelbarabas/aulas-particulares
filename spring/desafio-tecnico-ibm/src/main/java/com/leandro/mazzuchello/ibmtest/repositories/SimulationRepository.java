package com.leandro.mazzuchello.ibmtest.repositories;

import com.leandro.mazzuchello.ibmtest.entities.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Long> {
    List<Simulation> findAllByPeopleCpf(String cpf);
}
