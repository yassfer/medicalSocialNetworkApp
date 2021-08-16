package com.health.talan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.health.talan.entities.Community;
import com.health.talan.entities.User;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface CommunityRepository extends CrudRepository<Community, Long> {
	@Transactional
	@Modifying
	@Query("DELETE from Community com where com.id= :id")
	void deleteCommunityById(@Param("id") Long id);

	
	@Query("select c from Community c where c.adminCom = :user")
	List<Community> findByAdminCommunity(@Param("user")User user);
	
	
}
