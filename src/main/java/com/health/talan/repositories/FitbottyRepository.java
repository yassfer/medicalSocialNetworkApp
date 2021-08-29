package com.health.talan.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.health.talan.entities.Fitbotty;

@Repository
public interface FitbottyRepository extends CrudRepository<Fitbotty, Long>{

    public Fitbotty findByTitle(String title);
}
