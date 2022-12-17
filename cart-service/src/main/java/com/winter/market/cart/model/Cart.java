package com.winter.market.cart.model;

import com.winter.market.api.dtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalPrice;


    public void add(ProductDto product) {

        for (CartItem item : items) {
            if (item.getProductId().equals(product.getProductId())) {
                item.incrementQty();
                recalculate();
            }
        }
        CartItem cartItem = new CartItem(product.getId(),product.getTitle(),1,product.getPrice(), product.getPrice());
        items.add(cartItem);
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    public void recalculate() {
        totalPrice = BigDecimal.ZERO;
        items.forEach(i -> totalPrice = totalPrice.add(i.getPrice()));
    }
}
