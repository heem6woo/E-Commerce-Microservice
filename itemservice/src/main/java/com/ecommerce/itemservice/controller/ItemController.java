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
@RequestMapping("/")
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
    public ResponseEntity<List<ItemDTO>> likeItems(@RequestParam("item-name") String itemName) throws Exception {
        // itemName을 서비스 계층에 전달하여 해당 이름을 포함하는 아이템 목록들을 검색합니다.
        System.out.println(itemName);
        List<ItemDTO> itemList = itemService.findItemDTOsByName(itemName);

        return ResponseEntity.ok(itemList); // 검색 결과를 OK 상태 코드와 함께 반환합니다.
    }


}
