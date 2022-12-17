package com.winter.market.core.service.product;

import com.winter.market.api.dtos.NotFoundExciton;
import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.entities.Product;
import com.winter.market.core.repository.ProductRepository;
import com.winter.market.core.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public void create(ProductDto productDto) {
        Product product = new Product();

        product.setProductId(productDto.getProductId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findByIdTitle(productDto.getCategoryTitle()).orElseThrow(() -> new NotFoundExciton("Продукт с title: " + productDto.getTitle() + " не найден")));

        productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
