package com.example.springboot_shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity{ //1

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //2

    private int orderPrice; // 주문가격

    private int count; // 수량


    public static OrderItem createOrderItem(Item item, int count) {

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice());

        item.removeStock(count);
        return orderItem;
    }

    public int getTotalPrice() {

        return orderPrice * count;
    }

    public void cancel() {

        this.getItem().addStock(count);
    }
}

/*
private LocalDateTime regTime;

private LocalDateTime updateTime;

1 기존에 있던 위 두 변수를 삭제한다 그리고 BaseEntity를 상속받도록 소스코드를 수정합니다.
 */

//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
