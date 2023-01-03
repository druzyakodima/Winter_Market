package com.winter.market.cart.model;

import com.winter.market.api.dtos.ProductDto;
import lombok.Data;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalPrice;


    public Cart() {
        this.items = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
    }

    public void add(ProductDto product) {

        for (CartItem item : items) {
            if (item.getProductId().equals(product.getId())) {
                item.incrementQty();
                recalculate();
                return;
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

    public void delete(Long id) {

        for (CartItem cartItem : items) {
            if (cartItem.getProductId().equals(id)) {
                cartItem.decrementQty();
                recalculate();

                if (cartItem.getQty() == 0) {
                    getItems().remove(cartItem);
                }
                return;
            }
        }
        recalculate();
    }
}
