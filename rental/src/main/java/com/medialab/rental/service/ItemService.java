package com.medialab.rental.service;

import com.medialab.rental.Item;
import com.medialab.rental.controller.ItemController;
import com.medialab.rental.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getItemByName(String itemName) {
        try {
            Item myItem = itemRepository.findByNameItem(itemName);
            if (myItem == null)
                throw new RuntimeException("Item with the following name not found: " + itemName);
            System.out.println(myItem);
            return myItem;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public List<Item> getAllItems() {
        List<Item> allItems = itemRepository.findAll();
        System.out.println(allItems);
        return allItems;
    }
}
