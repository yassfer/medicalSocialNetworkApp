package com.health.talan.Repository;

import com.health.talan.entities.PieceJoint;
import com.health.talan.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
