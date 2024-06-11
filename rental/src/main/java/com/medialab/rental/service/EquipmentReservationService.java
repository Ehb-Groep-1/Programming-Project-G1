package com.medialab.rental.service;

import com.medialab.rental.EquipmentReservation;
import com.medialab.rental.Item;
import com.medialab.rental.User;
import com.medialab.rental.repository.EquipmentReservationRepository;
import com.medialab.rental.repository.ItemRepository;
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
    private final ItemRepository itemRepository;

    @Autowired
    EquipmentReservationService(EquipmentReservationRepository equipmentReservationRepository, ItemRepository itemRepository) {
        this.equipmentReservationRepository = equipmentReservationRepository;
        this.itemRepository = itemRepository;
    }

}
