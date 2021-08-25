package com.health.talan.repositories;

import com.health.talan.entities.Entreprise;
import com.health.talan.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends CrudRepository<Entreprise, Long> {

    @Query("select e from Entreprise e where e.user = :user")
    Entreprise findByAdminEntreprise(@Param("user") User user);
}
