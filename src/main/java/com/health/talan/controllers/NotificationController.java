package com.health.talan.controllers;


import com.health.talan.entities.Notification;
import com.health.talan.services.serviceInterfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("notification/")

public class NotificationController {
    @Autowired
    private NotificationService notifService;

    @GetMapping("getAll")
    public List<Notification> getEntreprise() throws IOException {
        return notifService.GetAllNotif();
    }

    @PostMapping("create")
    public Notification setMessage(@RequestBody Notification notif) throws IOException {
        notifService.setNotif(notif);
        return notif;

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEntreprise(@PathVariable("id") Long id) throws IOException {

        String message = notifService.DeleteNotif(id);
        if (message.equals("Notification Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}