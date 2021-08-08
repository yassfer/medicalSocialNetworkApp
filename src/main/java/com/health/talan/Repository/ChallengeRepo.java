package com.health.talan.Repository;

import com.health.talan.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepo extends JpaRepository<Challenge, Long> {
}
