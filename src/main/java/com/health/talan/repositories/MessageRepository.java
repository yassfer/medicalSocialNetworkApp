package com.health.talan.repositories;

import java.util.List;
import java.util.Optional;

import com.health.talan.entities.Publication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;

import com.health.talan.entities.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    @Transactional
    @Query("SELECT m FROM Message m WHERE m.receiver = ?1")
    Optional<List<Message>> findByReceiverId(Long userId);

}