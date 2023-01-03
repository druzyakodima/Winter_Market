package com.winter.market.core.service.orders;

import com.winter.market.core.entities.Order;

public interface IOrdersService {
    void createOrder(String username, String phone, String address);
    Order findById(Long id);

}
