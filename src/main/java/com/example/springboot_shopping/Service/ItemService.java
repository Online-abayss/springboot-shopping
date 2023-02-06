package com.example.springboot_shopping.Service;

import com.example.springboot_shopping.dto.ItemFormDto;
import com.example.springboot_shopping.entity.Item;
import com.example.springboot_shopping.entity.ItemImg;
import com.example.springboot_shopping.repository.ItemImgRepository;
import com.example.springboot_shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws  Exception {

        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        for (int i =0; i<itemImgFileList.size(); i++) {

            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0) {

                itemImg.setRepimgYn("Y");
            }else {

                itemImg.setRepimgYn("N");
                itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
            }

        }

        return item.getId();
    }

}
