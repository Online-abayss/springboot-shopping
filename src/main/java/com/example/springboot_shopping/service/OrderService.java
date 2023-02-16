package com.example.springboot_shopping.service;

import com.example.springboot_shopping.dto.OrderDto;
import com.example.springboot_shopping.entity.Item;
import com.example.springboot_shopping.entity.Member;
import com.example.springboot_shopping.entity.Order;
import com.example.springboot_shopping.entity.OrderItem;
import com.example.springboot_shopping.repository.ItemRepository;
import com.example.springboot_shopping.repository.MemberRepository;
import com.example.springboot_shopping.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public Long order(OrderDto orderDto, String email) {

        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);

        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();

        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }
}
