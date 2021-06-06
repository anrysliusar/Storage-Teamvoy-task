package com.sliusar.storageteamvoytask.controllers;

import com.sliusar.storageteamvoytask.models.Item;
import com.sliusar.storageteamvoytask.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public List<Item> getAll() {
        return itemService.getAllItems();
    }

    @GetMapping("/cheapest")
    public List<Item> getAtLowestPrice(@RequestParam String name, @RequestParam int quantity) {
        return itemService.getItemsAtLowestPrice(name, quantity);
    }

    @PostMapping("/new")
    public void addNewItem(@RequestBody Item item) {
        itemService.saveItem(item);
    }
}
