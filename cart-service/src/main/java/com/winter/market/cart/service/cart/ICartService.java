package com.winter.market.cart.service.cart;

import com.winter.market.api.dtos.CartDto;

public interface ICartService {

    public void addToCart(Long id);

    public CartDto getCurrentCart();

    public void delete(Long id);

    public void clearCart();
}
