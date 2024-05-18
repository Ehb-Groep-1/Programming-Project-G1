package com.medialab.rental.repository;

import com.medialab.rental.CurrentItemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrentItemUserRepository extends JpaRepository<CurrentItemUser, Integer> {

//    List<CurrentItemUser> findByUserId(int userID);
//
//    List<CurrentItemUser> findByItemId(int itemID);


}
