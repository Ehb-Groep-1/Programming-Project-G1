package com.medialab.rental.repository;

import com.medialab.rental.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    //User findAllBy(String username, String email);

    User findByPhoneNumber(String phoneNumber); // misschien fout want in db is het phone_number
    @Query(value = "SELECT user FROM User user WHERE user.banned_date > local date")
    Collection<User> findAllBanned();

    @Query(value = "SELECT user FROM User user WHERE user.banned_date IS NULL OR user.banned_date < local date ")
    Collection<User> findAllUnbanned();


}
