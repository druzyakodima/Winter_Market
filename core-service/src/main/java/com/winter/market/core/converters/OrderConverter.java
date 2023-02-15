package com.winter.market.core.converters;

import com.winter.market.api.dtos.OrderDto;
import com.winter.market.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order o) {
        return new OrderDto(o.getId(), o.getItems()
                .stream()
                .map(orderItemConverter::entityToDto)
                .collect(Collectors.toList()), o.getTotalPrice());
    }
}
