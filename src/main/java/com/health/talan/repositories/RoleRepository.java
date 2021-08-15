package com.health.talan.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.health.talan.entities.Role;
import com.health.talan.entities.RoleName;

public interface RoleRepository extends CrudRepository<Role, Long>{
	 Optional<Role> findByName(RoleName roleName);
}
