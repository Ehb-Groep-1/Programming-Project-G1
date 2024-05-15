package com.medialab.rental;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "EquipmentPreviousUser")
public class EquipmentPreviousUser {
    @Id
    @Column(name = "id")
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_user_id")
    private User user;
    @Column(name = "return_date")
    private LocalDate returnDate;

    public EquipmentPreviousUser() {
    }

    public int getId() {
        return id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public User getUser() {
        return user;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

}
