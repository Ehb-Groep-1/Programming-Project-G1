package com.medialab.rental.repository;

import com.medialab.rental.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

//    List<Reservation> findByUserId(int userID);
//    List<Reservation> findByEquipmentId(int equipmentID);
//    List<Reservation> findByReservationDate(LocalDate reservationDate); //intelij gemaakt
//    List<Reservation> findByReservationDateBetween(LocalDate startDate, LocalDate endDate);//intelij gemaakt

}
