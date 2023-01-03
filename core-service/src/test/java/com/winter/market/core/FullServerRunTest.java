package com.winter.market.core;

import com.winter.market.api.dtos.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fullRestTest() {
        List<ProductDto> products = restTemplate.getForObject("/api/v1/products", List.class);
        assertThat(products).isNotNull();

        ProductDto productDto = restTemplate.getForObject("/api/v1/products/1", ProductDto.class);
        assertThat(productDto).isNotNull();

    }
}
