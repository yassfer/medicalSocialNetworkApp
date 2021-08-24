package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.Notification;

import java.io.IOException;
import java.util.List;

public interface NotificationService {
    List<Notification> GetAllNotif() throws IOException;
    Notification setNotif(Notification msg) throws IOException;
    String DeleteNotif(long id) throws IOException;


}
