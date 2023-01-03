package com.winter.market.core.service.orders;

import com.winter.market.api.dtos.CartDto;
import com.winter.market.api.dtos.NotFoundExciton;
import com.winter.market.core.entities.Order;
import com.winter.market.core.entities.OrderItem;
import com.winter.market.core.integration.CartServiceIntegration;
import com.winter.market.core.repository.IOrdersRepository;
import com.winter.market.core.service.product.IProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
public class OrdersService implements IOrdersService {

    private final CartServiceIntegration cartService;
    private final IProductService productService;
    private final IOrdersRepository ordersRepository;
    private final IOrderItemService orderItemService;

    @Transactional
    @Override
    public void createOrder(String username, String phone, String address) {

        CartDto cart = cartService.getCurrentCart();
        Order order = new Order();

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> new OrderItem(
                productService.findById(cartItem.getProductId()).orElseThrow(() -> new NotFoundExciton(String.format("Продукт %d не найден", cartItem.getProductId()))),
                order,
                cartItem.getQty(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice()
        )).collect(Collectors.toList());

        orderItems.forEach(orderItemService::save);

        order.setUsername(username);
        order.setPhone(phone);
        order.setAddress(address);
        order.setItems(orderItems);
        order.setTotalPrice(cart.getTotalPrice());

        ordersRepository.save(order);
        cartService.clear();
    }

    @Override
    public Order findById(Long id) {
        return ordersRepository.findById(id).orElseThrow(() -> new NotFoundExciton("Заказ не найден id: " + id));
    }
}
