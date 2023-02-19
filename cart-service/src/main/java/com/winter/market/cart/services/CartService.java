package com.winter.market.cart.services;

import com.winter.market.api.dtos.ProductDto;
import com.winter.market.cart.integration.ProductServiceIntegration;
import com.winter.market.cart.model.Cart;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final ProductServiceIntegration productService;
    private final RedisTemplate<String, Object> redisTemplate;
    public Cart getCurrentCart(String cartId) {

        if (!redisTemplate.hasKey(cartId)) {
            Cart cart = new Cart();
            redisTemplate.opsForValue().set(cartId, cart);
        }

        return (Cart) redisTemplate.opsForValue().get(cartId);
    }

    public void addToCart(String cartId ,Long id) {
        execute(cartId, cart -> {
            ProductDto productDto = productService.getProductById(id);
            cart.add(productDto);
        });
    }

    public void delete(String cartId ,Long id) {
        execute(cartId, cart -> cart.delete(id));
    }

    public void clearCart(String cartId) {
        execute(cartId, Cart::clear);
    }

    public void execute(String cartId, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartId);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartId, cart);
    }
}
