package com.medialab.rental;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ItemRentalHistory")
public class ItemRentalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "rental_date")
    private LocalDate rental_date;
    @Column(name = "return_date")
    private LocalDate return_date;
    @Column(name = "rental_duration")
    private int rental_duration;

    public ItemRentalHistory() {

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public LocalDate getRental_date() {
        return rental_date;
    }

    public void setRental_duration(int rental_duration) {
        this.rental_duration = rental_duration;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public void setRental_date(LocalDate rental_date) {
        this.rental_date = rental_date;
    }
}
