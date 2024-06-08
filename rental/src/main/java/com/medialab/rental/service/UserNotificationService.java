package com.medialab.rental.service;

import com.medialab.rental.Notification;
import com.medialab.rental.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserNotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    UserNotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public Collection<Notification> getNotifications(int userId){
        return notificationRepository.findAllByUserId(userId);
    }
}
