package com.medialab.rental.repository;

import com.medialab.rental.CurrentItemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrentItemUserRepository extends JpaRepository<CurrentItemUser, Integer> {

    CurrentItemUserRepository findByUser_id(String user_id);
    CurrentItemUserRepository findbyId(Integer id);
    CurrentItemUserRepository findbyitem_id(Integer item_id);

}
