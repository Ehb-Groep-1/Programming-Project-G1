package com.medialab.rental;

import jakarta.persistence.*;

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

    public String getPassword() { return password; }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBanned_date(LocalDate banned_date) {
        this.banned_date = banned_date;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
