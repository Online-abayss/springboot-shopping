package com.example.springboot_shopping.entity;

import com.example.springboot_shopping.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //1
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //2

    private LocalDateTime orderDate; //물건 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문상태

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //1,3
    private List<OrderItem> orderItems = new ArrayList<>(); //2

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    public void addOrderItem(OrderItem orderItem) {

        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList) {

        Order order = new Order();
        order.setMember(member);

        for(OrderItem orderItem : orderItemList) {

            order.addOrderItem(orderItem);
        }

        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    public int getTotalPrice() {

        int totalPrice = 0;

        for (OrderItem orderItem : orderItems) {

            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}

/*
1. 부모 엔티티의 영속성 상태 변화를 자식 엔티티에 모두 전이하는 CascadeTypeAll 옵션을 설정하겠습니다.


 */

/*
orphanRemoval = true -> 고아객체 삭제를 담당하는 역할
 */
//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
//https://dev-elop.tistory.com/entry/JPA-orphanRemoval-%EC%9A%A9%EB%8F%84
