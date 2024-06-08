package com.medialab.rental.repository;

import com.medialab.rental.Notification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    Notification findByUserId(int userID);

    @Query("SELECT notification FROM Notification notification WHERE notification.user.id = :userId")
    List<Notification> findAllByUserId(int userId);

    Notification findByDateSent(LocalDateTime dateSent);



}
