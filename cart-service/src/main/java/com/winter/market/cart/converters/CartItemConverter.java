package com.winter.market.cart.converters;

import com.winter.market.api.dtos.CartItemDto;
import com.winter.market.cart.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setQty(cartItem.getQty());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());

        return cartItemDto;
    }
}
