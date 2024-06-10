package com.medialab.rental.controller;

import com.medialab.rental.ItemRentalHistory;
import com.medialab.rental.Notification;
import com.medialab.rental.repository.ItemRepository;
import com.medialab.rental.repository.NotificationRepository;
import com.medialab.rental.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {
    private final UserNotificationService userNotificationService;
    private final SessionService sessionService;
    private final UserService userService;
    private final ItemRentalHistoryService itemRentalHistoryService;
    private final ItemService itemService;

    @Autowired
    NotificationController(UserNotificationService userNotificationService, SessionService sessionService, UserService userService, ItemRentalHistoryService itemRentalHistoryService, ItemService itemService) {
        this.userNotificationService = userNotificationService;
        this.sessionService = sessionService;
        this.userService = userService;
        this.itemRentalHistoryService = itemRentalHistoryService;
        this.itemService = itemService;
    }

    public record NotificationInfo(int id, int user_id, String message, LocalDateTime date_sent) {
    }

    @PostMapping("/notification")
    public ResponseEntity<Void> setNotifications(HttpSession session) {
        List<ItemRentalHistory> rentalHistory = (List<ItemRentalHistory>) itemRentalHistoryService.getAllForUser(sessionService.getUserInfo().userId());
        if (rentalHistory == null)
            throw new RuntimeException("No rental history for user found!");
        List<NotificationInfo> notificationInfos = new ArrayList<>();
        for (ItemRentalHistory history : rentalHistory) {
            String msg = "You rented a '" + history.getItem().getNameItem() + "'";
            Notification notification = userNotificationService.createNotification(userService.getCustomer(sessionService.getUserInfo().username()), msg);
            if (notification == null)
                throw new RuntimeException("Notification not found!");
            notificationInfos.add(new NotificationInfo(notification.getId(), notification.getUser().getUserID(), notification.getMessage(), notification.getDateSent()));
        }
        System.out.println(notificationInfos);
        session.setAttribute("notifications", notificationInfos);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/notification")
    public ResponseEntity<List<NotificationInfo>> getNotifications(HttpSession session) {
        List<NotificationInfo> notificationInfos = (List<NotificationInfo>) session.getAttribute("notifications");
        if (notificationInfos == null)
            throw new RuntimeException("No notifications found!");
        return ResponseEntity.ok(notificationInfos);
    }
}
