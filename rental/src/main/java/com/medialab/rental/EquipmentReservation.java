package com.medialab.rental;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;

@Entity
@Table(name = "EquipmentReservation")
public class EquipmentReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Item item;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    public EquipmentReservation(LocalDate start, LocalDate end) {
        this.startDate = start;
        this.endDate = end;
    }

    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDate date) {
        this.startDate = date;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
