package com.winter.market.core.service.product;

import com.winter.market.api.dtos.NotFoundExciton;
import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.entities.Product;
import com.winter.market.core.repository.ProductRepository;
import com.winter.market.core.service.category.CategoryService;
import com.winter.market.core.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public Page<Product> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page, pageSize));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void create(ProductDto productDto) {

         Product product = Product.Builder.newBuilder()
                 .withProductId(productDto.getId())
                 .withTitle(productDto.getTitle())
                 .withPrice(productDto.getPrice())
                 .withCategory(categoryService.findByIdTitle(productDto.getCategoryTitle()).orElseThrow(() -> new NotFoundExciton("Продукт с title: " + productDto.getTitle() + " не найден")))
                 .build();

        productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Specification<Product> findWithFilter(BigDecimal minPriceFilter,
                                                 BigDecimal maxPriceFilter,
                                                 String titleFilter) {

        Specification<Product> spec = Specification.where(null);

            if (minPriceFilter != null) {
                spec = spec.and(ProductSpecification.priceMin(minPriceFilter));
            }

            if (maxPriceFilter != null) {
                spec = spec.and(ProductSpecification.priceMax(maxPriceFilter));
            }

            if (titleFilter != null && !titleFilter.equals("")) {
                spec = spec.and(ProductSpecification.likeTitle(titleFilter));
            }

        return spec;
    }
}
