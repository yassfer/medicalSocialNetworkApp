package com.health.talan.Repository;

import com.health.talan.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.user.id = ?1 AND c.publication.id = ?2")
    Optional<Comment> findByUserAndPublication(Long userId, Long publicationId);
}
