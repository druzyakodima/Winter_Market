package com.winter.market.core.service.orders;



import com.winter.market.core.entities.Order;
import com.winter.market.core.entities.User;

public interface IOrdersService {
    void createOrder(User user, String phone, String address);
    Order findById(Long id);

}
