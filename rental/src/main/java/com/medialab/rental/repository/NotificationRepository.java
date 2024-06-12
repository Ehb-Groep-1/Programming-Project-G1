package com.medialab.rental.repository;

import com.medialab.rental.Notification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    Notification findByUserId(int userID);

    @Query(value = "SELECT * FROM Notification WHERE user_id = :userId", nativeQuery = true)
    List<Notification> findAllByUserId(@Param("userId") int userId);

    Notification findByDateSent(LocalDateTime dateSent);



}
