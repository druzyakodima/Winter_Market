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

    public CartDto getCurrentCart() {

        return cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new NotFoundExciton("Товар не найден в продуктовом МС"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear() {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new NotFoundExciton("Товар не найден в продуктовом МС"))
                )
                .toBodilessEntity()
                .block();
    }
}
