package com.medialab.rental.controller;

import com.medialab.rental.Item;
import com.medialab.rental.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Collection;
import java.util.Optional;

@RequestMapping(value="/item")
@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    public record ItemCreation(String name, String description, int quantity, Optional<Integer> lastUserId){}
    @PostMapping("/")
    public ResponseEntity<Void> addItem(@RequestBody ItemCreation itemCreation) {
        itemService.createItem(itemCreation.name, itemCreation.description, itemCreation.quantity, itemCreation.lastUserId.orElse(-1));
        return ResponseEntity.noContent().build();
    }

    public record ItemInfo(int id, String name, String description) {}
    @GetMapping("/info")
    public ResponseEntity<Collection<ItemInfo>> itemInfo() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items.stream().map(item -> new ItemInfo(item.getItemID(), item.getNameItem(), item.getDescriptionItem())).toList());
    }
}
