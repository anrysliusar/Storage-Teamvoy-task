package com.sliusar.storageteamvoytask.services.implementation;


import com.sliusar.storageteamvoytask.models.Orders;
import com.sliusar.storageteamvoytask.repository.OrdersRepository;
import com.sliusar.storageteamvoytask.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrdersRepository ordersRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Orders saveOrder(Orders order) {
        order.setCreatedAt(new Date());
        Orders newOrder = ordersRepository.save(order);
        return newOrder;
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public void deleteIfNotValid() {
        var dateNow = new Date();
        var date_10Min = new Date(dateNow.getTime() - 600000); //time of 10 minutes ago
        ordersRepository.deleteAllByCreatedAtBefore(date_10Min);
    }
}
