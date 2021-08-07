package com.health.talan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.talan.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
