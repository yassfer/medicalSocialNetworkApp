package com.health.talan.services;

import java.io.IOException;
import java.util.List;

import com.health.talan.entities.Message;


public interface MessageService {
    List<Message> GetMsg() throws IOException;
    Message setMessage(Message msg) throws IOException;
}