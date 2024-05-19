package com.medialab.rental.service;

import com.medialab.rental.User;
import com.medialab.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Transactional(readOnly = true)
    public User getCustomer(String userName) {
        try {
            User myUser = userRepository.findUserById(1);
            if (myUser == null) {
                throw new RuntimeException("User with the following name doesn't exist: " + userName);
            }
            System.out.println("This is the name of the user: " + myUser.getUsername());
            return myUser;
        } catch (Exception e) {
            System.err.println(e.getCause());
        }
        return null;
    }
}
