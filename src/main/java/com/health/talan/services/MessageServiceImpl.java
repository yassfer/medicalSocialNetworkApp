package com.health.talan.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.talan.entities.Message;
import com.health.talan.repositories.MessageRepository;
import com.health.talan.services.serviceInterfaces.MessageService;

@Service
public class MessageServiceImpl implements MessageService {


    public MessageRepository messagerepository ;

    @Autowired
    public MessageServiceImpl(MessageRepository messagerepository) {
        this.messagerepository = messagerepository;
    }

    @Override
    public List<Message> GetMsg() throws IOException{

        return (List<Message>)messagerepository.findAll();
    }

    @Override
    public Message setMessage (Message msg) throws IOException {
        messagerepository.save(msg);
        return msg;
    }

    @Override
    public Optional<List<Message>> getByReceiver (Long id) throws IOException {
        return messagerepository.findByReceiverId(id);
    }
}
