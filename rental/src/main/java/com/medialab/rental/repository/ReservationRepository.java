package com.medialab.rental.repository;

import com.medialab.rental.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    Reservation findByUserId(int userID); //user_id in db als het niet werkt

    Reservation findById(int id);


}
