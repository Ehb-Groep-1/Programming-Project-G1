package com.medialab.rental.controller;

import com.medialab.rental.ItemRentalHistory;
import com.medialab.rental.User;
import com.medialab.rental.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserNotificationService userNotificationService;
    private final SessionService sessionService;
    private final ItemRentalHistoryService itemRentalHistoryService;

    @Autowired
    UserController(UserService userService, UserNotificationService userNotificationService,
                   SessionService sessionService, ItemRentalHistoryService itemRentalHistoryService) {
        this.userService = userService;
        this.userNotificationService = userNotificationService;
        this.sessionService = sessionService;
        this.itemRentalHistoryService = itemRentalHistoryService;
    }

    @GetMapping("/userinfo")
    public ResponseEntity<SessionService.UserInfo> userInfo(){
        return ResponseEntity.ok(sessionService.getUserInfo());
    }

    public record UserProfile(String username, String email, String address, String phonenumber){}
    @GetMapping("/userprofile")
    public ResponseEntity<UserProfile> userProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getCustomer(authentication.getName());
        UserProfile userProfile = new UserProfile(currentUser.getUsername(), currentUser.getEmail(), currentUser.getAdres(), currentUser.getPhoneNumber());

        return ResponseEntity.ok(userProfile);
    }

    public record UserProfileChange(String password, String email, String address, String phonenumber){}
    @PutMapping("/userprofile")
    public ResponseEntity<Void> putUserProfile(@RequestBody UserProfileChange userProfileChange){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getCustomer(authentication.getName());

        if(userProfileChange.password.isEmpty()) {
            userService.saveCustomer(authentication.getName(), userProfileChange.email, userProfileChange.phonenumber,
                    userProfileChange.address, currentUser.getRole());
        } else {
            userService.saveCustomer(authentication.getName(), userProfileChange.email, userProfileChange.phonenumber,
                    userProfileChange.address, currentUser.getRole(), userProfileChange.password);
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody Map<String, String> response) {
        User created= userService.registerCustomer(response.get("username"), response.get("password"), response.get("email"), response.get("phonenumber"), response.get("address"));
        return ResponseEntity.ok(created);
    }


    public record UserNotification(String message, String dateSent){}
    @GetMapping(value ="/notifications")
    public ResponseEntity<Collection<UserNotification>> notifications(){
        Collection<UserNotification> notifications = userNotificationService.getNotifications(sessionService.getUserInfo().userId())
                .stream().map(n -> new UserNotification(n.getMessage(), n.getDateSent().toString())).toList();

        return ResponseEntity.ok(notifications);
    }

    public record UserRentalHistory(String itemName, String itemDescription, LocalDate rentalDate, LocalDate returnDate, int rentalDuration){}
    @GetMapping(value = "/rentalhistory")
    public ResponseEntity<Collection<UserRentalHistory>> getRentalHistory(){
        Collection<UserRentalHistory> rentalList = itemRentalHistoryService.getAllForUser(sessionService.getUserInfo().userId())
                .stream().map(this::convert).toList();

        return ResponseEntity.ok(rentalList);
    }
    private UserRentalHistory convert(ItemRentalHistory itemRentalHistory){
        return new UserRentalHistory(itemRentalHistory.getItem().getNameItem(),
                itemRentalHistory.getItem().getDescriptionItem(),
                itemRentalHistory.getRental_date(),
                itemRentalHistory.getReturn_date(),
                itemRentalHistory.getRental_duration());
    }

    public record BannedUser(String username, int userId, LocalDate bannedUntil){}
    @GetMapping(value = "/bannedusers")
    @PreAuthorize(value = "hasAuthority('admin')")
    public ResponseEntity<Collection<BannedUser>> getBannedUsers(){
        return ResponseEntity.ok(userService.getBannedCustomers().stream()
                .map(u -> new BannedUser(u.getUsername(), u.getUserID(), u.getBanned_date()))
                .toList());
    }

    @PostMapping(value = "/unban")
    @PreAuthorize(value = "hasAuthority('admin')")
    public ResponseEntity<Void> unbanUser(@RequestBody BannedUser bannedUser){
        userService.unbanUser(bannedUser.userId);

        return ResponseEntity.noContent().build();
    }

    public record UnbannedUser(String username, int userId){}
    @GetMapping(value = "/unbannedusers")
    @PreAuthorize(value = "hasAuthority('admin')")
    public ResponseEntity<Collection<UnbannedUser>> getUnbannedUsers(){
        return ResponseEntity.ok(userService.getUnbannedCustomers().stream()
                .map(u -> new UnbannedUser(u.getUsername(), u.getUserID()))
                .toList());
    }

    @PostMapping(value = "/ban")
    @PreAuthorize(value = "hasAuthority('admin')")
    public ResponseEntity<Void> banUser(@RequestBody BannedUser bannedUser){
        userService.banUser(bannedUser.userId);

        return ResponseEntity.noContent().build();
    }
}
