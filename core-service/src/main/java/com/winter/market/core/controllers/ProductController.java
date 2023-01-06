package com.winter.market.core.controllers;

import com.winter.market.api.dtos.NotFoundExciton;
import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.converters.ProductConverter;
import com.winter.market.core.entities.Product;
import com.winter.market.core.service.product.IProductService;
import com.winter.market.core.utils.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page<ProductDto> findAllProduct(@RequestParam(required = false, name = "priceMinFilter") BigDecimal priceMinFilter,
                                           @RequestParam(required = false, name = "priceMaxFilter") BigDecimal priceMaxFilter,
                                           @RequestParam(required = false, name = "titleFilter") String titleFilter,
                                           @RequestParam(defaultValue = "1", name = "p") Integer page,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        if (page < 1) {
            page = 1;
        }

        Specification<Product> spec = productService.findWithFilter(priceMinFilter,priceMaxFilter,titleFilter);

        return productService.findAll(spec,page - 1, pageSize).map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findProductById(@PathVariable("id") Long id) {

        return productConverter
                .entityToDto(
                        productService
                                .findById(id)
                                .orElseThrow(() -> new NotFoundExciton("Продукт с id: " + id + " не найден"))
                );
    }

    @GetMapping("/remove/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@RequestBody ProductDto productDto) {
        productService.create(productDto);
    }
}
