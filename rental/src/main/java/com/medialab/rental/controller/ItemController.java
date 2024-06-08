package com.medialab.rental.controller;

import com.medialab.rental.Item;
import com.medialab.rental.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemController {
    private final ItemService itemService;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    ItemController(ItemService itemService, AuthenticationProvider authenticationProvider) {
        this.itemService = itemService;
        this.authenticationProvider = authenticationProvider;
    }

    public record ItemInfo(int id, String name, String description) {
    }

    @GetMapping("/iteminfo")
    public ResponseEntity<List<ItemInfo>> itemInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items.stream().map(item -> new ItemInfo(item.getItemID(), item.getNameItem(), item.getDescriptionItem())).collect(Collectors.toList()));
    }
}
