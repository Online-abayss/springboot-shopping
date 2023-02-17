package com.example.springboot_shopping.repository;


import com.example.springboot_shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o " + "where o.member.email = :email " + "order by o.orderDate desc")
    List<Order> findOrders(@Param("email") String email, Pageable pagable);

    @Query("select count(o) from Order o " + "where o.member.email = :email ")
    Long countOrder(@Param("email") String email);


}
