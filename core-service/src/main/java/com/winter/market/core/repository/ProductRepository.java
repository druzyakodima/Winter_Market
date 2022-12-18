package com.winter.market.core.repository;

import com.winter.market.core.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
