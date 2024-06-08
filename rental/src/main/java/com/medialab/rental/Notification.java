package com.medialab.rental;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "message")
    private String message;
    @Column(name = "date_sent")
    private LocalDateTime dateSent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public User getUser() {
        return user;
    }

    public Notification(String message, LocalDateTime dateSent) {
        this.message = message;
        this.dateSent = dateSent;
    }

    public Notification() {

    }

    public void setUser(User user) {
        this.user = user;
    }

}
