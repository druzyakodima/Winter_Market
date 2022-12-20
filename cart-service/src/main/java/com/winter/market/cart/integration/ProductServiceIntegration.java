package com.winter.market.cart.integration;

import com.winter.market.api.dtos.NotFoundExciton;
import com.winter.market.api.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    @Autowired
    public ProductServiceIntegration(WebClient productServiceWebClient) {
        this.productServiceWebClient = productServiceWebClient;
    }

    public ProductDto getProductById(@PathVariable("id") Long id) {

       return productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new NotFoundExciton("Товар не найден в продуктовом МС"))
                        )
                .bodyToMono(ProductDto.class)
                .block();

    }
}
