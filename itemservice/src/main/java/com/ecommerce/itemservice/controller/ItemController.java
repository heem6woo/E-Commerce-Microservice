package com.ecommerce.itemservice.controller;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;
//    @PostMapping("/")
//    public ResponseEntity<List<ItemDTO>> allItems()
//            throws Exception {
//            List<ItemDTO> N = null;
//        return (ResponseEntity<List<ItemDTO>>) ResponseEntity.ok(N);
//
//    }
    @GetMapping("/")
    public ResponseEntity<List<ItemDTO>> likeNameItemsSameCat(@RequestParam("item-name") String itemName,
                                                       @RequestParam(name = "item-cat", required = false) Short itemCat) throws Exception {
        // itemName을 서비스 계층에 전달하여 해당 이름을 포함하는 아이템 목록들을 검색합니다.
        List<ItemDTO> itemList;

        if (itemCat != null) {
            // itemCat 파라미터가 제공된 경우
            itemList = itemService.findItemDTOsByNameAndCat(itemName, itemCat);
            System.out.println("Searching by name and category");
        } else {
            // itemCat 파라미터가 제공되지 않은 경우
            itemList = itemService.findItemDTOsByName(itemName);
            System.out.println("Searching by name only");
        }

        return ResponseEntity.ok(itemList); // 검색 결과를 OK 상태 코드와 함께 반환합니다.
    }


}
