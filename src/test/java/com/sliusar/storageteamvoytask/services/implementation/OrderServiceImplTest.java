package com.sliusar.storageteamvoytask.services.implementation;

import com.sliusar.storageteamvoytask.models.Item;
import com.sliusar.storageteamvoytask.models.Orders;
import com.sliusar.storageteamvoytask.repository.OrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class OrderServiceImplTest {

    private OrderServiceImpl orderService;

    @Mock
    private OrdersRepository ordersRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(ordersRepository);
    }

    @Test
    void saveOrder() {
        Orders aMockOrder = new Orders();
        aMockOrder.setId(1);
        aMockOrder.setQuantity(10);
        aMockOrder.setPrice(300);
        aMockOrder.setItem(new Item());
        aMockOrder.setCreatedAt(new Date());
        when(ordersRepository.save(any(Orders.class))).thenReturn(aMockOrder);

        Orders newOrder = orderService.saveOrder(aMockOrder);

        assertNotNull(newOrder);
        assertEquals(10, newOrder.getQuantity());
        assertEquals(300, newOrder.getPrice());
    }

    @Test
    void getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        orders.add(new Orders());
        orders.add(new Orders());
        when(ordersRepository.findAll()).thenReturn(orders);
        List<Orders> orderList = orderService.getAllOrders();
        assertEquals(orders, orderList);
        verify(ordersRepository).findAll();

    }

    @Test
    void deleteIfNotValid() {
        Orders order = new Orders();
        order.setId(1);
        order.setQuantity(10);
        order.setPrice(300);
        order.setItem(new Item());
        Date dateNow = new Date();
        Date date_10Min = new Date(dateNow.getTime() - 600000);
        order.setCreatedAt(date_10Min);
        orderService.saveOrder(order);

        orderService.deleteIfNotValid();
        assertEquals(0, orderService.getAllOrders().size());
    }


}
