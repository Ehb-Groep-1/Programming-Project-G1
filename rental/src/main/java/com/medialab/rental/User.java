package com.medialab.rental;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userID;
    @Column(name = "username")
    private String username;
    @Column(name = "password_hash")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
//    private LocalDate banStartDate, banEndDate;
    @Column(name = "role")
    protected UserRole role;

    public User(String username, String password, String email, String phoneNumber, String adres, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = adres;
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdres() {
        return address;
    }

/*
    protected void setBanStartDate(LocalDate banStartDate) {
        this.banStartDate = banStartDate;
    }

    protected void setBanEndDate(LocalDate banEndDate) {
        this.banEndDate = banEndDate;
    }*/
}
