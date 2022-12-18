package com.winter.market.core.service.orders;

import com.winter.market.core.entities.OrderItem;
import com.winter.market.core.repository.IOrdersItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServer implements IOrderItemService{
    private final IOrdersItemRepository ordersItemRepository;

    @Override
    public void save(OrderItem orderItem) {
        ordersItemRepository.save(orderItem);
    }
}
