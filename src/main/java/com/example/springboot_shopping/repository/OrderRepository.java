package com.example.springboot_shopping.repository;

import com.example.springboot_shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
