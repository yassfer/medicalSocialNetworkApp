package com.health.talan.repositories;

import java.util.List;
import java.util.Optional;

import com.health.talan.entities.PublicationChallenge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.health.talan.entities.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByMail(String mail);

	@Transactional
	@Query("SELECT u FROM User u WHERE u.mail = ?1")
	Optional<User> findByMail(String mail);
}
