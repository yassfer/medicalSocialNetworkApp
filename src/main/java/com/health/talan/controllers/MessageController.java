package com.health.talan.controllers;

import com.health.talan.services.MessageService;
import com.health.talan.entities.Message;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("message/")
public class MessageController {

    @Autowired
    private MessageService msgService;

    @GetMapping("/getAll")
    public List<Message> getMsgbysenderAndReceiver() throws IOException {
        return msgService.GetMsg();

    }

    @GetMapping("/receiverId/{receiverId}")
    public List<Message> getAllMsgByReceiver(@PathVariable("receiverId") Long id) throws IOException {
        return msgService.getByReceiver(id).get();

    }

    @PostMapping("setMsg")
    public Message setMessage(@RequestBody Message msg)  throws IOException {
        msgService.setMessage(msg);
        return msg;

    }
}