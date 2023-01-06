package com.winter.market.core.service.product;

import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.entities.Product;
import com.winter.market.core.utils.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Optional;

public interface IProductService {

    Page<Product> findAll(Specification<Product> spec, int page,int pageSize);

    void deleteProductById(Long id);

    void create(ProductDto productDto);

    Optional<Product> findById(Long id);

    Specification<Product> findWithFilter(BigDecimal minPrice,
                                          BigDecimal maxPrice,
                                          String title);
}
