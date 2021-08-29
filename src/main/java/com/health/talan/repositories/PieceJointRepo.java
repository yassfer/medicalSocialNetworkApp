package com.health.talan.repositories;

import com.health.talan.entities.PieceJoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PieceJointRepo extends JpaRepository<PieceJoint, Long> {

    @Transactional
    @Query("SELECT p FROM PieceJoint p WHERE p.publication.id = ?1")
    Optional<List<PieceJoint>> findByPublicationId(Long publicationId);

    @Transactional
    @Modifying
    @Query("DELETE from PieceJoint p where p.publication.id = ?1")
    void deleteByPublicationId(Long id);


    @Transactional
    @Modifying
    @Query("DELETE from PieceJoint p where p.publicationChallenge.id = ?1")
    void deleteByPublicationChallengeId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE from PieceJoint p where p.publicationCommunity.id = ?1")
    void deleteByPublicationCommunityId(Long id);
}
