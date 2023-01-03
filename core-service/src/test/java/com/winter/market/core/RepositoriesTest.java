package com.winter.market.core;

import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.entities.Category;
import com.winter.market.core.entities.Product;
import com.winter.market.core.repository.ProductRepository;
import com.winter.market.core.utils.Filter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void productRepositoryTest() {

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");

        Product product = new Product();
        product.setProductId(19L);
        product.setTitle("Cheese");
        product.setCategory(category);
        product.setPrice(BigDecimal.valueOf(176));

        entityManager.persist(product);
        entityManager.flush();

        List<Product> products = productRepository.findAll();

        Assertions.assertEquals(5, products.size());
        Assertions.assertEquals("Food", products.get(1).getCategory().getTitle());
    }
}
