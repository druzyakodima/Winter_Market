package com.winter.market.core.service.product;

import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> findAll();

    void deleteProductById(Long id);

    void create(ProductDto productDto);

    Optional<Product> findById(Long id);

    List<ProductDto> findWithFilter(BigDecimal priceMinFilter,
                                    BigDecimal priceMaxFilter,
                                    String titleFilter);
}
