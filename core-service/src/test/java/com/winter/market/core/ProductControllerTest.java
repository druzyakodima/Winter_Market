package com.winter.market.core;

import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.entities.Product;
import com.winter.market.core.service.product.IProductService;
import com.winter.market.core.utils.Filter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private IProductService productService;

    @Test
    public void getAllGenresTest() throws Exception {

        List<Product> products = productService.findAll();
        given(productService.findAll()).willReturn(products);

        mvc
                .perform(
                        get("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        Filter filter = new Filter();
        filter.setTitleFilter("m");
        filter.setPriceMinFilter(BigDecimal.valueOf(30));
        filter.setPriceMaxFilter(BigDecimal.valueOf(60));

        List<ProductDto> productDtos = productService.findWithFilter(filter);
        given(productService.findWithFilter(filter)).willReturn(productDtos);

        mvc
                .perform(get("/api/v1/products/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }
}
