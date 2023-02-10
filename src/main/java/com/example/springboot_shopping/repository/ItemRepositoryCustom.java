package com.example.springboot_shopping.repository;

import com.example.springboot_shopping.dto.ItemSearchDto;
import com.example.springboot_shopping.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);


}
