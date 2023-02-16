package com.example.springboot_shopping.Service;

import com.example.springboot_shopping.constant.ItemSellStatus;
import com.example.springboot_shopping.dto.OrderDto;
import com.example.springboot_shopping.entity.Item;
import com.example.springboot_shopping.entity.Member;
import com.example.springboot_shopping.entity.Order;
import com.example.springboot_shopping.entity.OrderItem;
import com.example.springboot_shopping.repository.ItemRepository;
import com.example.springboot_shopping.repository.MemberRepository;
import com.example.springboot_shopping.repository.OrderItemRepository;
import com.example.springboot_shopping.repository.OrderRepository;
import com.example.springboot_shopping.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    public Item saveItem() {

        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setStockNumber(100);
        item.setItemSellStatus(ItemSellStatus.SELL);

        return itemRepository.save(item);
    }

    public Member saveMember() {

        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("주문 테스트")
    public void order() {

        Item item = saveItem();
        Member member =saveMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());

        Long orderId = orderService.order(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDto.getCount() * item.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());

    }
}
