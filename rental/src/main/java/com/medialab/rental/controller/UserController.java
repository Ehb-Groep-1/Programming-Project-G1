package com.medialab.rental.controller;

import com.medialab.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping("/api/login")
    public Map<String, String> loginUser(@RequestBody Map<String, String> response) {
        try {
//        System.out.println(response);
//        System.out.println(response.get("userName"));
//            System.out.println(userService.getCustomer(response.get("userName")).userDetails());
            return userService.getCustomer(response.get("userName")).userDetails();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
