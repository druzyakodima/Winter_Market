package com.winter.market.core.integration;

import com.winter.market.api.dtos.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

   private final RestTemplate restTemplate;

    public Optional<CartDto> getCurrentCart() {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8190/market-carts/api/v1/cart" ,CartDto.class));
    }
}
