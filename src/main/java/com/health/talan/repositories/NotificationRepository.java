package com.health.talan.repositories;

import com.health.talan.entities.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository  extends CrudRepository<Notification, Long> {

}
