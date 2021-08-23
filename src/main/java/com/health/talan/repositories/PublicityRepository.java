package com.health.talan.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.health.talan.entities.Publicity;
import com.health.talan.entities.User;

@Repository
public interface PublicityRepository extends JpaRepository<Publicity, Long> {

    Optional<List<Publicity>> findByUser(User user);

}