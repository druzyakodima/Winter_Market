package com.winter.market.core.converters;

import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDto entityToDto(Product product) {

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setProductId(product.getProductId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryTitle(product.getCategory().getTitle());

        return productDto;
    }
}
