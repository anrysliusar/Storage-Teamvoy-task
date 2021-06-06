package com.sliusar.storageteamvoytask.services.implementation;

import com.sliusar.storageteamvoytask.models.Item;
import com.sliusar.storageteamvoytask.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ItemServiceImplTest {

    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void saveItem() {
        var aMockItem = new Item();
        aMockItem.setId(1);
        aMockItem.setName("Book");
        aMockItem.setPrice(400);
        aMockItem.setQuantity(50);

        when(itemRepository.save(any(Item.class))).thenReturn(aMockItem);
        var newItem = itemService.saveItem(aMockItem);

        assertNotNull(newItem);
        assertEquals("Book", newItem.getName());
    }

    @Test
    void getAllItems() {
        var items = new ArrayList<Item>();
        items.add(new Item());
        items.add(new Item());
        when(itemRepository.findAll()).thenReturn(items);
        List<Item> itemList = itemService.getAllItems();
        assertEquals(items, itemList);
    }

    @Test
    void getItemAtLowestPrice() {
        var aMockItem = new Item();
        aMockItem.setName("Book");
        aMockItem.setQuantity(6);
        when(itemRepository.findAllByNameOrderByPriceAsc(aMockItem.getName())).thenReturn(List.of(aMockItem));
        var itemsAtLowestPrice = itemService.getItemsAtLowestPrice(aMockItem.getName(), 5);
        assertEquals(itemsAtLowestPrice.get(0).getQuantity(), 5);
    }
}
