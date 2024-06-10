package com.medialab.rental.service;

import com.medialab.rental.EquipmentReservation;
import com.medialab.rental.Item;
import com.medialab.rental.ItemRentalHistory;
import com.medialab.rental.User;
import com.medialab.rental.repository.EquipmentReservationRepository;
import com.medialab.rental.repository.ItemRentalHistoryRepository;
import com.medialab.rental.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Service
public class ItemRentalHistoryService {
    private final ItemRentalHistoryRepository itemRentalHistoryRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemRentalHistoryService(ItemRentalHistoryRepository itemRentalHistoryRepository, ItemRepository itemRepository) {
        this.itemRentalHistoryRepository = itemRentalHistoryRepository;
        this.itemRepository = itemRepository;
    }

    public Collection<ItemRentalHistory> getAllForUser(int userId) {
        return itemRentalHistoryRepository.findAllByUserId(userId);
    }

    @Transactional
    public ItemRentalHistory createRent(User user, Item item, LocalDate startDate, LocalDate endDate) {
        ItemRentalHistory itemRentalHistory = new ItemRentalHistory();
        itemRentalHistory.setUser(user);
        itemRentalHistory.setItem(item);
        itemRentalHistory.setRental_date(startDate);
        itemRentalHistory.setReturn_date(endDate);
        int duur = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        System.out.println("Duration: " + duur);
        itemRentalHistory.setRental_duration(duur);
        itemRentalHistoryRepository.save(itemRentalHistory);
        itemRepository.decrementQuantity(item.getItemID());
        return itemRentalHistory;
    }
}
