package com.medialab.rental.service;

import com.medialab.rental.ItemRentalHistory;
import com.medialab.rental.repository.ItemRentalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemRentalHistoryService {
    private final ItemRentalHistoryRepository itemRentalHistoryRepository;

    @Autowired
    public ItemRentalHistoryService(ItemRentalHistoryRepository itemRentalHistoryRepository) {
        this.itemRentalHistoryRepository = itemRentalHistoryRepository;
    }

    public Collection<ItemRentalHistory> getAllForUser(int userId) {
        return itemRentalHistoryRepository.findAllByUserId(userId);
    }
}
