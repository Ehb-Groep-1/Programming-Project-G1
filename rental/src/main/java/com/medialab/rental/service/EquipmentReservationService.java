package com.medialab.rental.service;

import com.medialab.rental.EquipmentReservation;
import com.medialab.rental.Item;
import com.medialab.rental.User;
import com.medialab.rental.repository.EquipmentReservationRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EquipmentReservationService {
    private final EquipmentReservationRepository equipmentReservationRepository;

    @Autowired
    EquipmentReservationService(EquipmentReservationRepository equipmentReservationRepository) {
        this.equipmentReservationRepository = equipmentReservationRepository;
    }

    @Transactional
    public EquipmentReservation createReservation(User user, Item item, LocalDate startDate, LocalDate endDate) {
        EquipmentReservation equipmentReservation = new EquipmentReservation();
        equipmentReservation.setUser(user);
        equipmentReservation.setItem(item);
        equipmentReservation.setStartDate(startDate);
        equipmentReservation.setEndDate(endDate);
        equipmentReservationRepository.save(equipmentReservation);
        return equipmentReservation;
    }

}
