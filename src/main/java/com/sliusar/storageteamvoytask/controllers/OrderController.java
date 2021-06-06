package com.sliusar.storageteamvoytask.controllers;

import com.sliusar.storageteamvoytask.models.Orders;
import com.sliusar.storageteamvoytask.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Orders> getAll() {
        return orderService.getAllOrders();
    }

    @PostMapping("/new")
    public void addNewOrder(@RequestBody Orders order) {
        orderService.saveOrder(order);
    }
}

