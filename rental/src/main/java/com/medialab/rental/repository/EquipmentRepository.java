package com.medialab.rental.repository;

import com.medialab.rental.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    Equipment findByName(String name);

    Equipment findById(int id);
    Equipment findByType(String type);

    Equipment findByLastUserID(int userID); //last_user_id


}

