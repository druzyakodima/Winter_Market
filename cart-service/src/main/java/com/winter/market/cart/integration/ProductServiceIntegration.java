package com.winter.market.cart.integration;

import com.winter.market.api.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/market/api/v1/products/" + id, ProductDto.class));
    }
}
