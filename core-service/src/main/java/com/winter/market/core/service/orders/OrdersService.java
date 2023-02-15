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
import java.util.Optional;
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
    public Order createOrder(String username, String phone, String address) {

        CartDto cart = cartService.getCurrentCart(username);

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Нельзя оформить заказ для пустой корзины");
        }

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
        cartService.clear(username);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public List<Order> findUserOrders(String username) {
        return ordersRepository.findAllByUsername(username);
    }

}
