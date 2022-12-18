package com.winter.market.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {
    private Long productId;
    private String productTitle;
    private Integer qty;
    private BigDecimal price;
    private BigDecimal pricePerProduct;

    public void incrementQty() {
        qty++;
        price = price.add(pricePerProduct);
    }

    public void decrementQty() {
        qty--;
        price = price.subtract(pricePerProduct);
    }
}
