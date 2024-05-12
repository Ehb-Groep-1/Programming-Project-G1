package com.medialab.rental;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Entity(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    private final LocalDateTime dateSent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private int userID;
    public Notification(String message, LocalDateTime dateSent) {
        this.message = message;
        this.dateSent = dateSent;
    }
}
