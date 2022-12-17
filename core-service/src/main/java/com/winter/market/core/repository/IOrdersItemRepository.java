package com.winter.market.core.repository;

import com.winter.market.core.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersItemRepository extends JpaRepository<OrderItem, Long> {
}
