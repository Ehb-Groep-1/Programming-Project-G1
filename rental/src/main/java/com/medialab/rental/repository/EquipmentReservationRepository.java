package com.medialab.rental.repository;

import com.medialab.rental.EquipmentReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EquipmentReservationRepository extends JpaRepository<EquipmentReservation, Integer> {

    EquipmentReservation findByUserId(int userID); //user_id in db

    EquipmentReservation findById(int id);

}
