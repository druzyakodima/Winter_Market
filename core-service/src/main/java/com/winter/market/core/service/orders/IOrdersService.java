package com.winter.market.core.service.orders;

import com.winter.market.core.entities.Order;

import java.util.List;

public interface IOrdersService {
    Order createOrder(String username, String phone, String address);


    public List<Order> findUserOrders(String username);
}
