package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
