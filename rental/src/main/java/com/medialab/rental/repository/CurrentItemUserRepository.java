package com.medialab.rental.repository;

import com.medialab.rental.CurrentItemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentItemUserRepository extends JpaRepository<CurrentItemUser, Integer> {

    @Query("SELECT currentItemUser FROM CurrentItemUser currentItemUser WHERE currentItemUser.user.id = :user_id")
    CurrentItemUserRepository findByUser_id(String user_id);
    @Query("SELECT currentItemUser FROM CurrentItemUser currentItemUser WHERE currentItemUser.id = :id")
    CurrentItemUserRepository findbyId(Integer id);
    @Query("SELECT currentItemUser FROM CurrentItemUser currentItemUser WHERE currentItemUser.item.itemID = :item_id")
    CurrentItemUserRepository findbyitem_id(Integer item_id);

}
