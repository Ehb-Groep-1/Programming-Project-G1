package com.medialab.rental.repository;

import com.medialab.rental.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item findByNameItem(String name);
    Item findByLastUserID(int userID); // in db is het last_user_id


}

