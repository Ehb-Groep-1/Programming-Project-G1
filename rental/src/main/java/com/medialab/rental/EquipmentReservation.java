package com.medialab.rental;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;

@Entity(name = "EquipmentReservation")
public class EquipmentReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private int userID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private int itemID;
    private LocalDate startDate, endDate;

    public EquipmentReservation(LocalDate start, LocalDate end) {
        this.startDate = start;
        this.endDate = end;
    }
}
