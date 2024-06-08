package com.medialab.rental.repository;

import com.medialab.rental.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item findByNameItem(String name);
    @Query("SELECT item FROM Item item WHERE item.lastUserID.id = :userID")
    Item findByLastUserID(int userID); // in db is het last_user_id

}

