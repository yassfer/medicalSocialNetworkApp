package com.health.talan.repositories;

import com.health.talan.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepo extends JpaRepository<Publication, Long> {

    @Transactional
    @Query("SELECT p FROM Publication p WHERE p.user.id = ?1")
    Optional<List<Publication>> findByUserId(Long userId);
    
    @Transactional
    @Modifying
    @Query("DELETE from Publication p where p.id = ?1")
    void deletePublicationById(Long id);

}

