package com.medialab.rental.repository;

import com.medialab.rental.ItemRentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRentalHistoryRepository extends JpaRepository<ItemRentalHistory, Integer> {

//    List<ItemRentalHistory> findByUserId(int userID);
//
//    List<ItemRentalHistory> findByItemId(int itemID);
}
