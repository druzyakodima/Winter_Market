package com.winter.market.core.specification;

import com.winter.market.core.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> priceMin(BigDecimal priceMin) {
        return (root, query, cb) -> cb.ge(root.get("price"), priceMin);
    }

    public static Specification<Product> priceMax(BigDecimal priceMax) {
        return (root, query, cb) -> cb.le(root.get("price"), priceMax);
    }

    public static Specification<Product> likeTitle(String title) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"),  String.format("%%%s%%", title)));
    }

}
