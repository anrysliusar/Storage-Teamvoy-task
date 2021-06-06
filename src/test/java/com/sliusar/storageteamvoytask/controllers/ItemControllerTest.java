package com.sliusar.storageteamvoytask.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sliusar.storageteamvoytask.models.Item;
import com.sliusar.storageteamvoytask.models.Orders;
import com.sliusar.storageteamvoytask.services.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {ItemController.class})
@ExtendWith(SpringExtension.class)
class ItemControllerTest {
    private final String URL_ITEMS = "/items";

    @Autowired
    private ItemController itemController;

    @MockBean
    private ItemService itemService;

    @Test
    void getAll() throws Exception {
        MockMvcBuilders.standaloneSetup(this.itemController)
                .build()
                .perform(MockMvcRequestBuilders
                        .get(URL_ITEMS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAtLowestPrice() throws Exception {
        MockMvcBuilders.standaloneSetup(this.itemController)
                .build()
                .perform(MockMvcRequestBuilders
                        .get(URL_ITEMS + "/cheapest")
                        .param("name", "Book")
                        .param("quantity", String.valueOf(5)))
                .andExpect(status().isOk());
    }

    @Test
    void addNewItem() throws Exception {
        Item item = new Item();
        item.setId(1);
        item.setOrders(new ArrayList<Orders>());
        item.setName("Book");
        item.setQuantity(5);
        item.setPrice(200);

        MockMvcBuilders.standaloneSetup(this.itemController)
                .build()
                .perform(MockMvcRequestBuilders
                        .post(URL_ITEMS + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isOk());
    }
}
