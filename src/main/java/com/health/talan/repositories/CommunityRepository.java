package com.health.talan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.talan.entities.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

}
