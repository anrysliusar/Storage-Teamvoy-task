package com.sliusar.storageteamvoytask.services.implementation;

import com.sliusar.storageteamvoytask.models.Item;
import com.sliusar.storageteamvoytask.repository.ItemRepository;
import com.sliusar.storageteamvoytask.services.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item saveItem(Item item) {
        Item newItem = itemRepository.save(item);
        return newItem;

    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsAtLowestPrice(String name, int quantity) {
        List<Item> chosenItems = itemRepository.findAllByNameOrderByPriceAsc(name);
        var resultItems = new ArrayList<Item>();
        int available = 0;
        int addedQuantity = 0;
        for (Item chosenItem : chosenItems) {
            available += chosenItem.getQuantity();
            if (available < quantity && available > 0) {
                var itemForAddingToResult = new Item(chosenItem);
                resultItems.add(itemForAddingToResult);
                addedQuantity += chosenItem.getQuantity();
                chosenItem.setQuantity(0);
                itemRepository.save(chosenItem);
            } else if (available >= quantity) {
                var itemForAddingToResult = new Item(chosenItem);
                itemForAddingToResult.setQuantity(quantity - addedQuantity);
                resultItems.add(itemForAddingToResult);
                chosenItem.setQuantity(chosenItem.getQuantity() - quantity + addedQuantity);
                itemRepository.save(chosenItem);
                return resultItems;
            }
        }
        return resultItems;
    }
}
