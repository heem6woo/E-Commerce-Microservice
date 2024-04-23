package com.ecommerce.itemservice.controller;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.service.ItemSearchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/items")
public class ItemSearchingController {
    @Autowired
    private ItemSearchingService itemService;
//    @PostMapping("/")
//    public ResponseEntity<List<ItemDTO>> allItems()
//            throws Exception {
//            List<ItemDTO> N = null;
//        return (ResponseEntity<List<ItemDTO>>) ResponseEntity.ok(N);
//
//    }
    @GetMapping("/")
    public ResponseEntity<List<ItemDTO>> likeNameItemsSameCat(@RequestParam(name = "item-name", required = false) String itemName,
                                                       @RequestParam(name = "item-cat", required = false) Short itemCat,
                                                              @RequestParam(name = "min-price", required = false) int minPrice,
                                                              @RequestParam(name = "max-price", required = false) int maxPrice) throws Exception {

        // itemName을 서비스 계층에 전달하여 해당 이름을 포함하는 아이템 목록들을 검색합니다.
        List<ItemDTO> itemList;
        if(itemName!= null && itemCat != null && minPrice != 0 && maxPrice != 0){ // 이름, 카테, 최소 최대  가격
            itemList = itemService.findItemDTOsByNameAndCatMinMax(itemName, itemCat,minPrice,maxPrice);
            System.out.println("Searching by name and category and min & max");
        }
        else if(itemName!= null && itemCat != null && minPrice != 0){// 이름, 카테, 최소  가격
            itemList = itemService.findItemDTOsByNameAndCatMin(itemName, itemCat,minPrice);
            System.out.println("Searching by name and category and min");
        }

        else if (itemName!= null && itemCat != null) {// 이름, 카테

            itemList = itemService.findItemDTOsByNameAndCat(itemName, itemCat);
            System.out.println("Searching by name and category");
        }
        else if (itemName!= null)// 이름
        {
            itemList = itemService.findItemDTOsByName(itemName);
            System.out.println("Searching by name only");
        }
        else if (itemCat!= null && minPrice != 0 && maxPrice != 0){//카테, 최소 최대  가격
            itemList = itemService.findItemDTOsByCatMinMax(itemCat,minPrice,maxPrice);
            System.out.println("Searching by min max only");
        }
        else if (itemCat!= null && minPrice != 0 ){//카테, 최소 가격
            itemList = itemService.findItemDTOsByCatMin(itemCat,minPrice);
            System.out.println("Searching by min only");
        }
        else if (itemCat!= null && maxPrice != 0 ){// 카테,최대  가격
            itemList = itemService.findItemDTOsByCatMax(itemCat,maxPrice);
            System.out.println("Searching by max only");
        }
        else itemList = null;

        return ResponseEntity.ok(itemList); // 검색 결과를 OK 상태 코드와 함께 반환합니다.
    }


}
