package com.winter.market.core;

import com.winter.market.api.dtos.CartDto;
import com.winter.market.api.dtos.CartItemDto;
import com.winter.market.api.dtos.ProductDto;
import com.winter.market.core.entities.Category;
import com.winter.market.core.entities.Order;
import com.winter.market.core.entities.Product;
import com.winter.market.core.integration.CartServiceIntegration;
import com.winter.market.core.repository.IOrdersRepository;
import com.winter.market.core.repository.ProductRepository;
import com.winter.market.core.service.orders.IOrderItemService;
import com.winter.market.core.service.orders.OrdersService;
import com.winter.market.core.service.product.IProductService;
import com.winter.market.core.service.product.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class OrderServiceTests {

    @Autowired
    private OrdersService ordersService;
    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private IProductService productService;

    @MockBean
    private IOrderItemService orderItemService;

    @MockBean
    private IOrdersRepository ordersRepository;

    @Test
    public void createOrderTest() {
        CartDto cartDto = new CartDto();
        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setProductTitle("Juice");
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(120));
        cartItemDto.setQty(2);
        cartItemDto.setPrice(BigDecimal.valueOf(240));
        cartItemDto.setProductId(19L);

        cartDto.setTotalPrice(BigDecimal.valueOf(240));
        cartDto.setItems(List.of(cartItemDto));

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart();

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Other");

        Product product = new Product();
        product.setId(19L);
        product.setPrice(BigDecimal.valueOf(120));
        product.setTitle("Juice");
        product.setCategory(category);

        Mockito.doReturn(Optional.of(product)).when(productService).findById(19L);

        Order order = ordersService.createOrder("dima", "54534534", "dssd");

        Assertions.assertEquals(BigDecimal.valueOf(240), order.getTotalPrice());
        Mockito.verify(ordersRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
