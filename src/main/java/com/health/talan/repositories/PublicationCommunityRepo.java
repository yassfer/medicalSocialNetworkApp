package com.health.talan.repositories;

import com.health.talan.entities.PublicationChallenge;
import com.health.talan.entities.PublicationCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationCommunityRepo extends JpaRepository<PublicationCommunity, Long> {

    @Transactional
    @Query("SELECT p FROM PublicationCommunity p WHERE p.user.id = ?1")
    Optional<List<PublicationCommunity>> findByUserId(Long userId);


    @Transactional
    @Query("SELECT p FROM PublicationCommunity p WHERE p.community.id = ?1")
    Optional<List<PublicationCommunity>> findByCommunityId(Long communityId);


    @Query("select p from PublicationCommunity p ORDER BY p.dateCreation DESC")
    List<PublicationCommunity> getAllByDate();

    @Transactional
    @Modifying
    @Query("DELETE from PublicationCommunity p where p.id = ?1")
    void deletePublicationById(Long id);

    @Transactional
    @Modifying
    @Query("DELETE from PublicationCommunity p where p.community.id = ?1")
    void deleteByCommunityId(Long id);
}
