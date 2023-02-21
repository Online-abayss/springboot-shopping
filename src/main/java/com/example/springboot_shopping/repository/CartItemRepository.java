package com.example.springboot_shopping.repository;

import com.example.springboot_shopping.dto.CartDetailDto;
import com.example.springboot_shopping.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.example.springboot_shopping.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc"
    )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);

}

// 생성자를 이용하여 DTO를 반환할 때는 new 키워드와 해당 DTo의 패키지, 클래스명을 적어준다. 그리고 생성자의 파라미터는 DTO 클래스의 명시한 순으로 넣어준다.
