package com.health.talan.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.talan.entities.Invitation;
import com.health.talan.entities.User;
import com.health.talan.repositories.InvitationRepository;
import com.health.talan.repositories.UserRepository;

@Service
public class InvitationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    public Optional<List<Invitation>> getAllInvitations(Long userId) {
        User user = this.userRepository.findById(userId).get();
        return invitationRepository.findByReceiver(user);
    }

    public void DeleteInvitation (Long userId, Invitation invi) {
        User user = this.userRepository.findById(userId).get();
        user.getInvitationsReceive().remove(invi);
        userRepository.save(user);
    }

   /* public void AcceptInvitation (Long userId, Invitation invi) {
        User user = this.userRepository.findById(userId).get();
        User sender = invi.getSender();
        Amis senderAmis = new Amis();
        senderAmis.setMyFriend(sender);
        user.getAmis().add(senderAmis);
        userRepository.save(user);
    }*/
}
