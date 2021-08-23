package com.health.talan.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.health.talan.entities.Invitation;
import com.health.talan.entities.User;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Optional<List<Invitation>> findByReceiver(User user);
}
