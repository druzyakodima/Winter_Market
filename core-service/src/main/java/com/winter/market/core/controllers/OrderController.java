package com.winter.market.core.controllers;

import com.winter.market.core.service.orders.IOrdersService;
import com.winter.market.core.utils.OrderData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrdersService ordersService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderData orderData) {

        ordersService.createOrder(username, orderData.getPhone(), orderData.getAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ordersService.findById(id), HttpStatus.OK);
    }
}
