package com.winter.market.core.service.orders;

import com.winter.market.core.entities.OrderItem;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderItemService {

    @Transactional
    void save(OrderItem orderItem);
}
