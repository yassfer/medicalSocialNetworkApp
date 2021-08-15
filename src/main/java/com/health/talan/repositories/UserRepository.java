package com.health.talan.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.health.talan.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByMail(String mail);
}
