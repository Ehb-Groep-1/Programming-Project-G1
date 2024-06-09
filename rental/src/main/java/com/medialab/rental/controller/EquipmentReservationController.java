package com.medialab.rental.controller;

import com.medialab.rental.Item;
import com.medialab.rental.User;
import com.medialab.rental.service.EquipmentReservationService;
import com.medialab.rental.service.ItemService;
import com.medialab.rental.service.SessionService;
import com.medialab.rental.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservation")
public class EquipmentReservationController {
    private final EquipmentReservationService equipmentReservationService;
    private final ItemService itemService;
    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    EquipmentReservationController(EquipmentReservationService equipmentReservationService, ItemService itemService, SessionService sessionService, UserService userService) {
        this.equipmentReservationService = equipmentReservationService;
        this.itemService = itemService;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    public record ItemInfo(int id, String name, String description, int amountAvailable) {
    }

    public record Dates(String first, String second) {
    }

    public record Values(Dates dates, List<ItemInfo> itemsToRent) {
    }

    @GetMapping("/values")
    public ResponseEntity<Values> EquipmentReservationValues(HttpSession session) {
        Values values = (Values) session.getAttribute("values");
        if (values == null) values = new Values(new Dates("", ""), List.of());
        return ResponseEntity.ok(values);
    }

    @PostMapping("/values")
    public ResponseEntity<Void> createReservation(@RequestBody Values values, HttpSession session) {
        System.out.println(values);
        session.setAttribute("values", values);
        User user = userService.getCustomer(sessionService.getUserInfo().username());
        LocalDate first = LocalDate.parse(values.dates.first);
        LocalDate second = LocalDate.parse(values.dates.second);
        List<Item> items = values.itemsToRent.stream().map(itemInfo -> itemService.getItemByName(itemInfo.name)).collect(Collectors.toList());
        items.forEach(item -> equipmentReservationService.createReservation(user, item, first, second));
        return ResponseEntity.ok().build();
    }
}
