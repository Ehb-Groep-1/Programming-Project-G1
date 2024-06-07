package com.medialab.rental.repository;

import com.medialab.rental.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    Equipment findByName(String name);

    Equipment findById(int id);
    Equipment findByType(String type);

    @Query("SELECT equipment FROM Equipment equipment WHERE equipment.lastUserID.id = :userID")
    Equipment findByLastUserID(int userID); //last_user_id


}

