package com.medialab.rental;

import java.time.LocalDate;

public class Admin extends User {
    public Admin(String username, String password, String email, String phoneNumber, String adres, UserRole userRole) {
        super(username, password, email, phoneNumber, adres, userRole);
    }

    public void banUser(User user, LocalDate begin, LocalDate end) {
//        user.setBanStartDate(begin);
//        user.setBanEndDate(end);
    }

    public void addAdmin(User user) {
        user.role = UserRole.ADMIN;
    }
}
