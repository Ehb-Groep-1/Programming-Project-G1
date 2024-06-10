package com.medialab.rental.service;

import com.medialab.rental.Item;
import com.medialab.rental.User;
import com.medialab.rental.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;

    @Autowired
    ItemService(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
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
    public List<Item> getAllItemsAvailableItems() {
        return itemRepository.findAllAvailableItems();
    }

    public Item createItem(String name, String description, int quantity, int lastUserId) {
        Item item = new Item();
        Optional<User> lastUser = userService.getCustomerById(lastUserId);

        item.setNameItem(name);
        item.setDescriptionItem(description);
        item.setAvailableQuantity(quantity);
        item.setLastUserID(lastUser.orElse(null));

        itemRepository.save(item);
        return item;
    }
}
