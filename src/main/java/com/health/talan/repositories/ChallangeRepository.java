package com.health.talan.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.health.talan.entities.Challenge;
import com.health.talan.entities.User;


@Repository
public interface ChallangeRepository extends CrudRepository<Challenge, Long>{
	
	@Transactional
	@Modifying
	@Query("DELETE from Challenge c where c.id= :id")
	void deleteChallengeById(@Param("id") Long id);
	@Query("select c from Challenge c where c.adminChallenge = :user")
	List<Challenge> findByAdminChallenge(@Param("user")User user);
	
	@Query("select c from Challenge c ORDER BY c.createdAt DESC")
	List<Challenge> getAllByDate();
}
