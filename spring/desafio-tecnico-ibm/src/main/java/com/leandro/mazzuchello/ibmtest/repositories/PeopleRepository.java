package com.leandro.mazzuchello.ibmtest.repositories;

import com.leandro.mazzuchello.ibmtest.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    List<People> findAllByHasRestriction(Boolean hasRestriction);

    People findPeopleByCpf(String cpf);
}
