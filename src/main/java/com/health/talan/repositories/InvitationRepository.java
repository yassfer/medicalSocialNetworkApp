package com.health.talan.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.health.talan.entities.Invitation;
import com.health.talan.entities.User;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Optional<List<Invitation>> findByReceiver(User user);
    @Transactional
    @Modifying
    @Query("DELETE from Invitation i where i.id = :id")
    void deleteById(@Param("id")Long id);
}
