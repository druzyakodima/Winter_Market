package com.winter.market.core.controllers;

import com.winter.market.api.dtos.NotFoundExciton;
import com.winter.market.core.entities.User;
import com.winter.market.core.service.orders.IOrdersService;
import com.winter.market.core.service.user.UserService;
import com.winter.market.core.utils.OrderData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final IOrdersService ordersService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderData orderData) {

        User user = userService
                .findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundExciton(String.format("Пользователь %s не найден", principal.getName())));

        ordersService.createOrder(user,orderData.getPhone(),orderData.getAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ordersService.findById(id), HttpStatus.OK);
    }
}
