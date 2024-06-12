package com.medialab.rental.controller;

import com.medialab.rental.Item;
import com.medialab.rental.User;
import com.medialab.rental.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/history")
public class ItemRentalHistoryController {
    private final ItemRentalHistoryService itemRentalHistoryService;
    private final ItemService itemService;
    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    ItemRentalHistoryController(ItemRentalHistoryService itemRentalHistoryService, ItemService itemService, SessionService sessionService, UserService userService) {
        this.itemRentalHistoryService = itemRentalHistoryService;
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

    @GetMapping("/rent")
    public ResponseEntity<Values> ItemRentalHistoryValues(HttpSession session) {
        Values values = (Values) session.getAttribute("rent");
        if (values == null) values = new Values(new Dates("", ""), List.of());
        return ResponseEntity.ok(values);
    }

    @PostMapping("/rent")
    public ResponseEntity<Void> rentItem(@RequestBody Values values, HttpSession session) {
        System.out.println(values);
        session.setAttribute("rent", values);
        User user = userService.getCustomer(sessionService.getUserInfo().username());
        LocalDate first = LocalDate.parse(values.dates.first);
        LocalDate second = LocalDate.parse(values.dates.second);
        List<Item> items = values.itemsToRent.stream().map(itemInfo -> itemService.getItemByName(itemInfo.name)).collect(Collectors.toList());
        for (Item item : items) {
            itemRentalHistoryService.createRent(user, item, first, second);
        }
        return ResponseEntity.ok().build();
    }
}
