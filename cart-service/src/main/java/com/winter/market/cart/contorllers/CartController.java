package com.winter.market.cart.contorllers;

import com.winter.market.api.dtos.CartDto;
import com.winter.market.cart.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/cart")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartDto findAll() {
        return cartService.getCurrentCart();
    }

    @PutMapping("/add/{id}")
    public void add(@PathVariable("id") Long id) {
        cartService.addToCart(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        cartService.delete(id);
    }

    @DeleteMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }
}
