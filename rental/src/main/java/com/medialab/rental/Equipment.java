package com.medialab.rental;

import jakarta.persistence.*;

@Entity
@Table(name = "Equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int equipmentID;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private String type;
    @Column(name = "quantity_available")
    private int availableQuantity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User lastUserID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private EquipmentReservation equipmentReservationId;

    public Equipment(String name, String description, String type, int availableQuantity) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.availableQuantity = availableQuantity;
    }

    public Equipment() {
        // default constructor nog afwerken, vragen naar wat gemiddeld materiaal
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public User getLastUserID() {
        return lastUserID;
    }

    public void setLastUserID(User lastUserID) {
        this.lastUserID = lastUserID;
    }

    public EquipmentReservation getEquipmentReservationId() {
        return equipmentReservationId;
    }

    public void setEquipmentReservationId(EquipmentReservation equipmentReservationId) {
        this.equipmentReservationId = equipmentReservationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String toString() {
        return "Equipment => {\n" +
                "ID:\t" + getEquipmentID() +
                "Name:\t" + getName() +
                "Description:\t" + getDescription() +
                "Type:\t" + getType() +
                "Available Quantity:\t" + getAvailableQuantity() +
                "ID of Last User:\t" + getLastUserID() + "\n}";
    }
}

