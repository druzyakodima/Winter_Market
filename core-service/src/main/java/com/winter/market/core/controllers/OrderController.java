package com.winter.market.core.controllers;

import com.winter.market.api.dtos.OrderDto;
import com.winter.market.core.converters.OrderConverter;
import com.winter.market.core.service.orders.IOrdersService;
import com.winter.market.core.utils.OrderData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrdersService ordersService;

    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderData orderData) {
        ordersService.createOrder(username, orderData.getPhone(), orderData.getAddress());
    }

    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username) {
        return ordersService.findUserOrders(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
