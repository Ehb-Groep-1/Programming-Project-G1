package com.medialab.rental.repository;

import com.medialab.rental.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item findByNameItem(String name);
    @Query("SELECT item FROM Item item WHERE item.lastUserID.id = :userID")
    Item findByLastUserID(int userID); // in db is het last_user_id
    @Query(value = "SELECT * FROM Item WHERE quantity_available > 0", nativeQuery = true)
    List<Item> findAllAvailableItems();
    @Modifying
    @Transactional
    @Query(value = "UPDATE Item i SET i.quantity_available = i.quantity_available - 1 WHERE i.id = :itemId AND i.quantity_available > 0", nativeQuery = true)
    int decrementQuantity(@Param("itemId") int itemId);
}

