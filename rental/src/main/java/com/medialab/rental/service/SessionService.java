package com.medialab.rental.service;

import com.medialab.rental.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    private final ObjectFactory<HttpSession> sessionFactory;
    private final UserService userService;

    @Autowired
    public SessionService(ObjectFactory<HttpSession> sessionFactory, UserService userService) {
        this.sessionFactory = sessionFactory;
        this.userService = userService;
    }

    public record UserInfo(String username, String role, int userId) {
    }

    public UserInfo getUserInfo() {
        HttpSession session = sessionFactory.getObject();
        if (session.getAttribute("userId") == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getCustomer(authentication.getName());
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            session.setAttribute("userId", user.getUserID());
            session.setAttribute("userName", user.getUsername());
            session.setAttribute("userRole", user.getRole());
        }

        return new UserInfo(String.valueOf(session.getAttribute("userName")), String.valueOf(session.getAttribute("userRole")), (Integer) session.getAttribute("userId"));
    }

}
