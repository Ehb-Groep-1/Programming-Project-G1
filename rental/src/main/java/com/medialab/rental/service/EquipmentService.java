package com.medialab.rental.service;

import com.medialab.rental.Equipment;
import com.medialab.rental.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment getEquipmentByName(String equipmentName) {
        try {
            Equipment myEquipment = equipmentRepository.findByName(equipmentName);
            if (myEquipment == null) {
                throw new RuntimeException("Equipment with the following name not found: " + equipmentName);
            }
            System.out.println(myEquipment);
            return myEquipment;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
