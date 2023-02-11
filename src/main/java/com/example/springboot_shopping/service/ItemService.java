package com.example.springboot_shopping.service;

import com.example.springboot_shopping.dto.ItemFormDto;
import com.example.springboot_shopping.dto.ItemImgDto;
import com.example.springboot_shopping.dto.ItemSearchDto;
import com.example.springboot_shopping.dto.MainItemDto;
import com.example.springboot_shopping.entity.Item;
import com.example.springboot_shopping.entity.ItemImg;
import com.example.springboot_shopping.repository.ItemImgRepository;
import com.example.springboot_shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    public Long saveItem(@Valid ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {


        //상품 등록
        Item item = itemFormDto.createItem();//1
        itemRepository.save(item);//2

        //이미지 등록
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if (i == 0)//3
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));//4
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemFormDto getItemDtl(Long itemId) {

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();

        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);

        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }

    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {

        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);

        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        for (int i =0; i<itemImgFileList.size(); i++) {

            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }
        return item.getId();
    }

    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }

}

/*
1 -> 상품 등록 폼으로부터 입력 받은 데이터를 이용하여 item 객체를 생성합니다.

2-> 상품 데이터를 저장합니다

3-> 첫 번째 이미지일 경우 대표 상품 이미지 여부 값을 "Y"로 세팅 나머지 상품 이미지는 N으로 세팅합니다

4 -> 상품 이미지 정보를 저장합니다.

*/

//참고 자료 -> 백타불여일견 스프링부트와 쇼핑몰 with JPA 저자 변구훈님
