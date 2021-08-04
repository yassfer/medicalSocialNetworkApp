package com.health.talan.repositories;

import org.springframework.data.repository.CrudRepository;

import com.health.talan.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
