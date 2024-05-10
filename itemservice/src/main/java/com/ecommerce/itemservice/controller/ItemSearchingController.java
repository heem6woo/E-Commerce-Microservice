package com.ecommerce.itemservice.controller;


import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.dto.ItemListDTO;
import com.ecommerce.itemservice.service.ItemSearchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/item-search/")
public class ItemSearchingController {
    @Autowired
    private ItemSearchingService itemSearchingService;
    @GetMapping("getitems/")
    public ResponseEntity<List<ItemListDTO>> likeNameItemsSameCat(@RequestParam(name = "item-name", defaultValue = "-1",required = false) String itemName,
                                                                  @RequestParam(name = "cat-name", defaultValue = "-1",required = false) String catName,
                                                                  @RequestParam(name = "min-price", defaultValue = "-1",required = false) int minPrice,
                                                                  @RequestParam(name = "max-price", defaultValue = "-1",required = false) int maxPrice) throws Exception {
        System.out.println("name" + itemName + "cat" + catName + "min" + minPrice + "max" + maxPrice);
        // itemName을 서비스 계층에 전달하여 해당 이름을 포함하는 아이템 목록들을 검색합니다.
        List<ItemListDTO> itemList;
        if (!itemName.equals("-1") && !catName.equals("-1") && minPrice != -1 && maxPrice != -1) { // 이름, 카테, 최소 최대  가격
            itemList = itemSearchingService.findItemListDTOsByNameAndCatMinMax(itemName, catName, minPrice, maxPrice);
            System.out.println("Searching by name and category and min & max");
        } else if (!itemName.equals("-1") && !catName.equals("-1") && minPrice != -1) {// 이름, 카테, 최소  가격
            itemList = itemSearchingService.findItemListDTOsByNameAndCatMin(itemName, catName, minPrice);
            System.out.println("Searching by name and category and min");
        } else if (!itemName.equals("-1") && !catName.equals("-1")) {// 이름, 카테

            itemList = itemSearchingService.findItemListDTOsByNameAndCat(itemName, catName);
            System.out.println("Searching by name and category");
        } else if (!itemName.equals("-1"))// 이름
        {
            itemList = itemSearchingService.findItemListDTOsByName(itemName);
            System.out.println("Searching by name only");
        } else if (!catName.equals("-1") && minPrice != -1 && maxPrice != -1) {//카테, 최소 최대  가격
            itemList = itemSearchingService.findItemListDTOsByCatMinMax(catName, minPrice, maxPrice);
            System.out.println("Searching by min max only");
        } else if (!catName.equals("-1") && minPrice != -1) {//카테, 최소 가격
            itemList = itemSearchingService.findItemListDTOsByCatMin(catName, minPrice);
            System.out.println("Searching by min only");
        } else if (!catName.equals("-1") && maxPrice != -1) {// 카테,최대  가격
            itemList = itemSearchingService.findItemListDTOsByCatMax(catName, maxPrice);
            System.out.println("Searching by max only");
        } else itemList = null;
        return ResponseEntity.ok(itemList); // 검색 결과를 OK 상태 코드와 함께 반환합니다.
    }

    @GetMapping("getitem/")
    public ResponseEntity<ItemDTO> www(@RequestParam(name = "item-name", defaultValue = "-1", required = false) String itemName,
                                                 @RequestParam(name = "seller-id", defaultValue = "-1", required = false) int sellerId
    ) throws Exception {
        System.out.println("name" + itemName);
        // itemName을 서비스 계층에 전달하여 해당 이름을 포함하는 아이템 목록들을 검색합니다.
        List<ItemListDTO> itemList = null;
        List<ItemListDTO> itemDTOList = itemSearchingService.findItemListDTOsByName(itemName);
        System.out.println("얻어오기 성공" + itemDTOList.get(0).getItemValues().getItemId());
        ItemDTO itemDTO = itemSearchingService.findItemDTOByItemNameAndSellerId(sellerId, itemName);
        System.out.println("얻어오기 2성공" + itemDTO.getItemValues().getItemId());

        System.out.println("Searching by name and category and min & max");
        return ResponseEntity.ok(itemDTO); // 검색 결과를 OK 상태 코드와 함께 반환합니다.

    }

}



