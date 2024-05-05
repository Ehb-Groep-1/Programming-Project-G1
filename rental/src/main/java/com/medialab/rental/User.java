package com.medialab.rental;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String username, password, email, phoneNumber, adres;
    private int rentalCount;
    private LocalDate banStartDate, banEndDate;

    public User(String username, String password, String email, String phoneNumber, String adres, int rentalCount) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adres = adres;
        this.rentalCount = rentalCount;
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
        return adres;
    }

    public int getRentalCount() {
        return rentalCount;
    }

    protected void setBanStartDate(LocalDate banStartDate) {
        this.banStartDate = banStartDate;
    }

    protected void setBanEndDate(LocalDate banEndDate) {
        this.banEndDate = banEndDate;
    }
}
