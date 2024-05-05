package com.medialab.rental;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemID;
    private int lastUserID, availableQuantity;
    private String nameItem, descriptionItem;

    public Item(String name, String description, int lastUser, int availableQuantity) {
        this.nameItem = name;
        this.descriptionItem = description;
        this.lastUserID = lastUser;
        this.availableQuantity = availableQuantity;
    }

    public int getItemID() {
        return this.itemID;
    }

    public int getLastUserID() {
        return lastUserID;
    }

    public void setLastUserID(int lastUserID) {
        this.lastUserID = lastUserID;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getDescriptionItem() {
        return descriptionItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }
}
