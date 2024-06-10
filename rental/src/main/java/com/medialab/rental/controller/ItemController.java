package com.medialab.rental.controller;

import com.medialab.rental.Item;
import com.medialab.rental.service.ItemService;
import com.medialab.rental.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

@RequestMapping(value = "/item")
@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    public record ItemCreation(String name, String description, int quantity, Optional<Integer> lastUserId) {
    }

    @PostMapping("/")
    public ResponseEntity<Void> addItem(@RequestBody ItemCreation itemCreation) {
        itemService.createItem(itemCreation.name, itemCreation.description, itemCreation.quantity, itemCreation.lastUserId.orElse(-1));
        return ResponseEntity.noContent().build();
    }

    public record ItemInfo(int id, String name, String description, int amountAvailable) {
    }

    @GetMapping("/info")
    public ResponseEntity<Collection<ItemInfo>> itemInfo() {
        List<Item> items = itemService.getAllItemsAvailableItems();
        return ResponseEntity.ok(items.stream().map(item -> new ItemInfo(item.getItemID(), item.getNameItem(), item.getDescriptionItem(), item.getAvailableQuantity())).toList());
    }

    @GetMapping("/rent")
    public ResponseEntity<List<ItemInfo>> rentedItems(HttpSession session) {
        List<ItemInfo> rentedItems = (List<ItemInfo>) session.getAttribute("rentedItems");
        if (rentedItems == null)
            rentedItems = new ArrayList<>();
        return ResponseEntity.ok(rentedItems);
    }

    @PostMapping("/rent")
    public ResponseEntity<Void> rentItem(@RequestBody ItemInfo itemInfo, HttpSession session) {
        List<ItemInfo> rentedItems = (List<ItemInfo>) session.getAttribute("rentedItems");
        if (rentedItems == null) {
            rentedItems = new ArrayList<>();
        }
        rentedItems.add(itemInfo);
        session.setAttribute("rentedItems", rentedItems);
        return ResponseEntity.ok().build();
    }
}
