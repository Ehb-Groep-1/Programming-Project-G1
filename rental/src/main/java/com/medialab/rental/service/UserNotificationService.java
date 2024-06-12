package com.medialab.rental.service;

import com.medialab.rental.Notification;
import com.medialab.rental.User;
import com.medialab.rental.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class UserNotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    UserNotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Collection<Notification> getNotifications(int userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    public Notification createNotification(User user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setDateSent(LocalDateTime.now());
        boolean isUnique = true;
        for (Notification nf : getNotifications(user.getUserID())) {
            if (nf.getMessage().equals(notification.getMessage()) && nf.getDateSent().toLocalDate().equals(notification.getDateSent().toLocalDate())) {
                isUnique = false;
                break;
            }
        }
        if (isUnique)
            notificationRepository.save(notification);
        return notification;
    }
}
