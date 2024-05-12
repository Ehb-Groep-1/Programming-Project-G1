package com.medialab.rental;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User lastUserID;
    private int availableQuantity;
    private String nameItem, descriptionItem;

    public Item(String name, String description, int availableQuantity) {
        this.nameItem = name;
        this.descriptionItem = description;
        this.availableQuantity = availableQuantity;
    }

    public int getItemID() {
        return this.itemID;
    }

    public int getLastUserID() {
        return lastUserID.getUserID();
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
