package com.medialab.rental.repository;

import com.medialab.rental.EquipmentPreviousUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentPreviousUserRepository extends JpaRepository<EquipmentPreviousUser, Integer> {

//    List<EquipmentPreviousUser> findByEquipmentId(int equipmentID);

}
