package com.sliusar.storageteamvoytask.services;

import com.sliusar.storageteamvoytask.models.Item;

import java.util.List;

public interface ItemService {
    Item saveItem(Item item);

    List<Item> getAllItems();

    List<Item> getItemsAtLowestPrice(String name, int quantity);

}
