package com.winter.market.cart.contorllers;

import com.winter.market.api.dtos.CartDto;
import com.winter.market.cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/cart")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartDto findAll() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void add(@PathVariable("id") Long id) {
        cartService.addToCart(id);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        cartService.delete(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }
}
