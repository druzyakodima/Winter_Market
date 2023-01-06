package com.winter.market.core.integration;

import com.winter.market.api.dtos.CartDto;
import com.winter.market.api.dtos.NotFoundExciton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;

    @Autowired
    public CartServiceIntegration(WebClient cartServiceWebClient) {
        this.cartServiceWebClient = cartServiceWebClient;
    }

    public CartDto getCurrentCart(String username) {

        return cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
