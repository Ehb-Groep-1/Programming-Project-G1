package com.medialab.rental.repository;

import com.medialab.rental.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);

    User findUserByUsername(String username);

    User findByEmail(String email);

//    List<User> findAll();

}
