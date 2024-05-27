package com.medialab.rental.service;

import com.medialab.rental.Item;
import com.medialab.rental.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

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
}
