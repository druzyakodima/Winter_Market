package com.winter.market.cart.services;

import com.winter.market.api.dtos.CartDto;
import com.winter.market.api.dtos.ProductDto;
import com.winter.market.cart.converters.CartConverter;
import com.winter.market.cart.integration.ProductServiceIntegration;
import com.winter.market.cart.model.Cart;
import com.winter.market.cart.model.CartItem;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private Cart cart;
    private final CartConverter cartConverter;
    private final ProductServiceIntegration productService;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public void addToCart(Long id) {

        ProductDto productDto = productService.getProductById(id);

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProductId().equals(productDto.getId())) {

                cartItem.incrementQty();
                cart.recalculate();

                return;
            }
        }

        cart.add(productDto);
    }

    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cart);
    }

    public void delete(Long id) {

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProductId().equals(id)) {
                cartItem.decrementQty();
                cart.recalculate();

                if (cartItem.getQty() == 0) {
                    cart.getItems().remove(cartItem);
                }

                return;
            }
        }

        cart.recalculate();
    }

    public void clearCart() {
        cart.clear();
    }
}
