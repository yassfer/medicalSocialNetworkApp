package com.health.talan.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.talan.entities.Invitation;
import com.health.talan.services.InvitationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("invitations/")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @GetMapping("getInvitationByUser/{userId}")
    public Optional<List<Invitation>> getAllInvitations(@PathVariable Long userId) {
        return this.invitationService.getAllInvitations(userId);

    }
    @DeleteMapping("delete/{userId}")
    public void DeleteInvitation (@PathVariable Long userId, @RequestBody Invitation invi) {
        this.invitationService.DeleteInvitation(userId, invi);
    }

    /*@PostMapping("accept/{userId}")
    public void AcceptInvitation (@PathVariable Long userId, @RequestBody Invitation invi) {
        this.invitationService.AcceptInvitation(userId, invi);
    }*/
}

