package com.medialab.rental.repository;

import com.medialab.rental.ItemRentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ItemRentalHistoryRepository extends JpaRepository<ItemRentalHistory, Integer> {

    ItemRentalHistory findByUserId(int userID);

    @Query("SELECT itemRentalHistory FROM ItemRentalHistory itemRentalHistory WHERE itemRentalHistory.item.itemID = :itemID")
    ItemRentalHistory findByItemId(int itemID);

    @Query("SELECT itemRentalHistory FROM ItemRentalHistory itemRentalHistory WHERE itemRentalHistory.user.id = :userId")
    Collection<ItemRentalHistory> findAllByUserId(int userId);
}
