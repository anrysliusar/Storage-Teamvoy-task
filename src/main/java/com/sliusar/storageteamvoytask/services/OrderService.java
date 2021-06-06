package com.sliusar.storageteamvoytask.services;

import com.sliusar.storageteamvoytask.models.Orders;

import java.util.List;

public interface OrderService {
    Orders saveOrder(Orders order);

    List<Orders> getAllOrders();

    void deleteIfNotValid();
}
