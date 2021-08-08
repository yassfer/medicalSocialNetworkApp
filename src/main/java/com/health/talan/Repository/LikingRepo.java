package com.health.talan.Repository;

import com.health.talan.entities.Liking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikingRepo extends JpaRepository<Liking, Long> {

    @Query("SELECT l FROM Liking l WHERE l.user.id = ?1 AND l.publication.id = ?2")
    Optional<Liking> findByUserAndPublication(Long userId, Long publicationId);
}
