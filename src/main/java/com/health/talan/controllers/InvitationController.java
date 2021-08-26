package com.health.talan.controllers;

import java.util.List;
import java.util.Optional;

import com.health.talan.entities.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.talan.entities.Invitation;
import com.health.talan.entities.User;
import com.health.talan.services.InvitationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("invitations/")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @GetMapping("getInvitationByUser/{userId}")
    public ResponseEntity<List<Invitation>> getAllInvitations(@PathVariable Long userId) {
        Optional<List<Invitation>> invitations =  invitationService.getAllInvitations(userId);
        return new ResponseEntity<>(invitations.get(), HttpStatus.OK);

    }

    @DeleteMapping("delete/{idInvitation}")
    public void DeleteInvitation (@PathVariable Long idInvitation) {
        this.invitationService.deleteInvitation(idInvitation);
    }
    @GetMapping("add/{senderId}/{receiverId}")
    public void addInvitation(@PathVariable Long senderId, @PathVariable Long receiverId) {
        this.invitationService.addInvitation(senderId, receiverId);
    }
    @GetMapping("accept/{userId}/{inviId}")
    public void AcceptInvitation (@PathVariable Long userId, @PathVariable Long inviId ) {
        this.invitationService.acceptInvitation(userId, inviId);
    }
    
    @GetMapping("acceptUser/{idSender}/{idReceiver}")
    public void AcceptUser (@PathVariable Long idSender, @PathVariable Long idReceiver ) {
        this.invitationService.acceptInvitation(idSender, idReceiver);
    }

    @GetMapping("getBySender/{id}")
    public List<Invitation> getAllBySender(@PathVariable Long id){
        return invitationService.getAllBySender(id);
    }
}



