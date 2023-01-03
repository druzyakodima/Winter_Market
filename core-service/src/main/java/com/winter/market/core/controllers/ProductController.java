package com.winter.market.core.controllers;

import com.winter.market.api.dtos.NotFoundExciton;
import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.converters.ProductConverter;
import com.winter.market.core.service.product.IProductService;
import com.winter.market.core.utils.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> findAllProduct() {

        return productService
                .findAll()
                .stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList());
    }


    @PostMapping("/filter")
    public List<ProductDto> findProductWithFilter(@RequestBody Filter filter) {
        List<ProductDto> productDtos = productService.findWithFilter
                (filter.getPriceMinFilter(),
                        filter.getPriceMaxFilter(),
                        filter.getTitleFilter());

        return productDtos;

    }

    @GetMapping("/{id}")
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
