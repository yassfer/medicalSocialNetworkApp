package com.health.talan.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.health.talan.entities.Message;


public interface MessageService {
    List<Message> GetMsg() throws IOException;
    Message setMessage(Message msg) throws IOException;
    Optional<List<Message>> getByReceiver(Long id) throws IOException;
}