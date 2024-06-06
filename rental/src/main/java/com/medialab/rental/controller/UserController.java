package com.medialab.rental.controller;

import com.medialab.rental.User;
import com.medialab.rental.service.CustomUserDetailsService;
import com.medialab.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody Map<String, String> response) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(response.get("username"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,response.get("password"));
            Authentication authentication = authenticationProvider.authenticate(token);
            if(authentication.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok(userService.getCustomer(response.get("username")));
            }
            throw new BadCredentialsException("Bad credentials offered");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody Map<String, String> response) {
        User created= userService.registerCustomer(response.get("username"), response.get("password"), response.get("email"), response.get("phonenumber"), response.get("address"));
        return ResponseEntity.ok(created);
    }
}
