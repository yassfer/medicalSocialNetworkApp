package com.health.talan.Repository;

import com.health.talan.entities.PublicationChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationChallengeRepo extends JpaRepository<PublicationChallenge, Long> {

    @Transactional
    @Query("SELECT p FROM PublicationChallenge p WHERE p.user.id = ?1")
    Optional<List<PublicationChallenge>> findByUserId(Long userId);


    @Transactional
    @Query("SELECT p FROM PublicationChallenge p WHERE p.challenge.id = ?1")
    Optional<List<PublicationChallenge>> findByChallengeId(Long challengeId);


    @Transactional
    @Query("SELECT p FROM PublicationChallenge p WHERE p.approuved = ?1 AND p.challenge.id = ?2")
    Optional<List<PublicationChallenge>> findByApprouved(Boolean approuved, Long challengeId);

    @Transactional
    @Query("SELECT p FROM PublicationChallenge p WHERE p.user.id = ?1 AND p.challenge.id = ?2")
    Optional<PublicationChallenge> findByChallengeAndUser(Long UserId, Long challengeId);

}
