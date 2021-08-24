package com.health.talan.services;

import com.health.talan.entities.Notification;
import com.health.talan.repositories.NotificationRepository;
import com.health.talan.services.serviceInterfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    public NotificationRepository notifrepository ;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notifrepository) {
        this.notifrepository = notifrepository;
    }

    @Override
    public List<Notification> GetAllNotif() throws IOException {

        return (List<Notification>)notifrepository.findAll();
    }
    @Override
    public Notification setNotif (Notification notif) throws IOException {
        notifrepository.save(notif);
        return notif;
    }

    @Override
    public String DeleteNotif (long id) throws IOException {
        notifrepository.deleteById(id);
        return "Notification Deleted";
    }
}
