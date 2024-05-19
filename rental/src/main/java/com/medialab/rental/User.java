package com.medialab.rental;

import jakarta.persistence.*;
import org.springframework.beans.factory.support.ManagedArray;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
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
    @Column(name = "banned_date")
    private LocalDate banned_date;
    @Enumerated(EnumType.STRING)
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

    public User() {

    }

    public int getUserID() {
        return id;
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

    public LocalDate getBanned_date() {
        return banned_date;
    }

    public UserRole getRole() {
        return role;
    }

//    protected void setBanStartDate(LocalDate banStartDate) {
//        this.banStartDate = banStartDate;
//    }

    public Map<String, String> userDetails() {
        return Map.of("id", Integer.toString(getUserID()),
                "userName", getUsername(),
                "email", getEmail(),
                "phonenr", getPhoneNumber(),
                "address", getAdres(),
                "role", role.name(),
                "banDate", getBanned_date() + "");
    }

//    protected void setBanEndDate(LocalDate banEndDate) {
//        this.banEndDate = banEndDate;
//    }
}
