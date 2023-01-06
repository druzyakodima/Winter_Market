package com.winter.market.cart.contorllers;

import com.winter.market.api.dtos.CartDto;
import com.winter.market.api.dtos.StringResponse;
import com.winter.market.cart.converters.CartConverter;
import com.winter.market.cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;


@RequestMapping("/api/v1/cart")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private final CartConverter cartConverter;

    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.entityToDto(cartService.getCurrentCart(currentCartId));
    }

    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @GetMapping("/{guestCartId}/clear")
    public void clearCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCart(currentCartId);
    }

    private String selectCartId(String username, String guestCartId) {
        if (username != null) {
            return username;
        }
        return guestCartId;
    }

    @GetMapping("/{guestCartId}/delete/{productId}")
    public void delete(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.delete(currentCartId, productId);
    }
}
