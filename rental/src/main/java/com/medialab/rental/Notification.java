package com.medialab.rental;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateSent(LocalDateTime dateSent) {
        this.dateSent = dateSent;
    }

}
