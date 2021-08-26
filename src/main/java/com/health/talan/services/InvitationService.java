package com.health.talan.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
        Optional<List<Invitation>> invitations = invitationRepository.findByReceiver(user);

        Date toDay = new Date(System.currentTimeMillis());
        for (Invitation invitation : invitations.get()) {
            Date notifDay = invitation.getDate();
            long diffInMillies = Math.abs(toDay.getTime() - notifDay.getTime());

            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (diff == 0) {
                invitation.setTimeChecked(toDay);
                DateFormat dateFormat = new SimpleDateFormat("hh:mm");
                String timeString1 = dateFormat.format(invitation.getTimeChecked());
                String timeString2 = dateFormat.format(invitation.getTime());
                String[] fractions1 = timeString1.split(":");
                String[] fractions2 = timeString2.split(":");
                Integer hours1 = Integer.parseInt(fractions1[0]);
                Integer hours2 = Integer.parseInt(fractions2[0]);
                Integer minutes1 = Integer.parseInt(fractions1[1]);
                Integer minutes2 = Integer.parseInt(fractions2[1]);
                int hourDiff = hours1 - hours2;
                int minutesDiff = minutes1 - minutes2;
                if (minutesDiff < 0) {
                    minutesDiff = 60 + minutesDiff;
                    hourDiff--;
                }
                if (hourDiff < 0) {
                    hourDiff = 24 + hourDiff;
                }invitation.setSince(hourDiff + " hours and " + minutesDiff + " minutes ago");
            } else {
                invitation.setSince(diff + " days ago");
            }
        }
        return Optional.ofNullable(invitations).orElse(null);
    }

    public void deleteInvitation (Long idInvitation) {
        invitationRepository.deleteById(idInvitation);
    }

    public void addInvitation(Long senderId, Long receiverId) {
        Invitation invi = new Invitation();
        User sender = userRepository.findById(senderId).get();
        User receiver = userRepository.findById(receiverId).get();
        invi.setSender(sender);
        invi.setReceiver(receiver);
        System.out.println(System.currentTimeMillis());
        invi.setDate(new Date(System.currentTimeMillis()));
        System.out.println(invi.getDate());
        invi.setTime(new Date(System.currentTimeMillis()));
        invitationRepository.save(invi);
    }

    public void acceptInvitation (Long userId, Long inviId) {
        User user = this.userRepository.findById(userId).get();
        Invitation invi = invitationRepository.findById(inviId).get();
        User sender = invi.getSender();
        User user2 = userRepository.findById(invi.getReceiver().getId()).get();
        Set<User> friends2 = user2.getFriends();
        friends2.add(user);
        userRepository.save(user2);
        Set<User> friends = user.getFriends();
        friends.add(sender);
        user.setFriends(friends);
        userRepository.save(user);
        invitationRepository.deleteById(inviId);
    }
    
    public void acceptInvitationByUser (Long idSender, Long idReceiver) {
        User sender = this.userRepository.findById(idSender).get();
        User receiver = this.userRepository.findById(idReceiver).get();
        
        Invitation invi = invitationRepository.findInvitation(sender, receiver);
        Set<User> friendsS = sender.getFriends();
        friendsS.add(receiver);
        sender.setFriends(friendsS);
        Set<User> friendsR = receiver.getFriends();
        friendsR.add(sender);
        receiver.setFriends(friendsR);
        
        userRepository.save(sender);
        userRepository.save(receiver);
        invitationRepository.deleteById(invi.getId());
    }

    public List<Invitation> getAllBySender(Long id){
        User user = userRepository.findById(id).get();
        return invitationRepository.findBySender(user);
    }

}
