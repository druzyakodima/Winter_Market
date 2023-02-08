package com.winter.market.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    private Product(Builder builder) {
        setProductId(builder.productId);
        setTitle(builder.title);
        setPrice(builder.price);
        setCategory(builder.category);
        setCreatedAt(builder.createdAt);
        setUpdateAt(builder.updateAt);
    }

    public static Builder newBuilder(Product copy) {
        Builder builder = new Builder();
        builder.productId = copy.getProductId();
        builder.title = copy.getTitle();
        builder.price = copy.getPrice();
        builder.category = copy.getCategory();
        builder.createdAt = copy.getCreatedAt();
        builder.updateAt = copy.getUpdateAt();
        return builder;
    }

    public static final class Builder {
        private Long productId;
        private String title;
        private BigDecimal price;
        private Category category;
        private LocalDateTime createdAt;
        private LocalDateTime updateAt;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withProductId(Long val) {
            productId = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public Builder withPrice(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder withCategory(Category val) {
            category = val;
            return this;
        }

        public Builder withCreatedAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder withUpdateAt(LocalDateTime val) {
            updateAt = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
