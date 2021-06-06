package com.sliusar.storageteamvoytask.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sliusar.storageteamvoytask.models.Item;
import com.sliusar.storageteamvoytask.models.Orders;
import com.sliusar.storageteamvoytask.services.ItemService;
import com.sliusar.storageteamvoytask.services.OrderService;
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
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    private final String URL_ORDERS = "/orders";

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    @Test
    void getAll() throws Exception {
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(MockMvcRequestBuilders
                        .get(URL_ORDERS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addNewOrder() throws Exception {
        Orders order = new Orders();
        order.setId(1);
        order.setPrice(200);
        order.setQuantity(5);
        order.setCreatedAt(new Date());

        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(MockMvcRequestBuilders
                        .post(URL_ORDERS + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(status().isOk());
    }
}
