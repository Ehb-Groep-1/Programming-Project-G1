package com.medialab.rental;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "CurrentItemUser")
public class CurrentItemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "take_date")
    private LocalDate takeDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "rental_duration")
    private int rentalDuration;

    public CurrentItemUser() {

    }

    public int getId() {
        return id;
    }

    public LocalDate getTakeDate() {
        return returnDate;
    }

    public void setTakeDate(LocalDate takeDate) {
        this.takeDate = takeDate;
    }

    public LocalDate getReturnDate() {
        return takeDate;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
