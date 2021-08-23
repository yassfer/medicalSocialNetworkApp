package com.health.talan.repositories;

import com.health.talan.entities.Liking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LikingRepo extends JpaRepository<Liking, Long> {

    @Query("SELECT l FROM Liking l WHERE l.user.id = ?1 AND l.publication.id = ?2")
    Optional<Liking> findByUserAndPublication(Long userId, Long publicationId);

    @Transactional
    @Modifying
    @Query("DELETE from Liking l where l.publication.id = ?1")
    void deleteByPublicationId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE from Liking l where l.publicationChallenge.id = ?1")
    void deleteByPublicationChallengeId(Long id);
}
