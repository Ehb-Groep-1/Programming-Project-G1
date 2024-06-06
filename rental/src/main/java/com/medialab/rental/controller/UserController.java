package com.medialab.rental.controller;

import com.medialab.rental.User;
import com.medialab.rental.service.CustomUserDetailsService;
import com.medialab.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsService userDetailsService;

    @Autowired
    UserController(CustomUserDetailsService userDetailsService, AuthenticationProvider authenticationProvider, UserService userService) {
        this.userService = userService;
        this.authenticationProvider = authenticationProvider;
        this.userDetailsService = userDetailsService;
    }

    public record UserInfo(String username, List<String> role){}

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfo> userInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = new UserInfo(authentication.getName(), authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());

        return ResponseEntity.ok(userInfo);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody Map<String, String> response) {
        User created= userService.registerCustomer(response.get("username"), response.get("password"), response.get("email"), response.get("phonenumber"), response.get("address"));
        return ResponseEntity.ok(created);
    }
}
