package com.medialab.rental.repository;

import com.medialab.rental.EquipmentPreviousUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipmentPreviousUserRepository extends JpaRepository<EquipmentPreviousUser, Integer> {

    @Query("SELECT equipmentPreviousUser FROM EquipmentPreviousUser equipmentPreviousUser WHERE equipmentPreviousUser.equipment.equipmentID = :equipmentID")
    EquipmentPreviousUser findByEquipmentId(int equipmentID);


}
