package com.winter.market.core.specification;

import com.winter.market.core.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> priceMin(Integer priceMin) {
        return (root, query, cb) -> cb.ge(root.get("price"), priceMin);
    }

    public static Specification<Product> priceMax(Integer priceMax) {
        return (root, query, cb) -> cb.le(root.get("price"), priceMax);
    }

}
