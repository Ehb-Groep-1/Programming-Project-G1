package com.medialab.rental.repository;

import com.medialab.rental.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    Notification findByUserId(int userID);

    Notification findByDateSent(LocalDateTime dateSent);



}
