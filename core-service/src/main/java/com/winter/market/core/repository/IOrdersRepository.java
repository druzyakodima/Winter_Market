package com.winter.market.core.repository;

import com.winter.market.core.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IOrdersRepository extends JpaRepository<Order, Long> {
}
