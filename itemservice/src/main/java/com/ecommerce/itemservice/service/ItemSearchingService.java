package com.ecommerce.itemservice.service;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.dto.ItemListDTO;
import com.ecommerce.itemservice.dto.ItemValues;
import com.ecommerce.itemservice.dto.SalesValues;
import com.ecommerce.itemservice.entity.Item;
import com.ecommerce.itemservice.entity.SalesInfo;
import com.ecommerce.itemservice.repository.costumer.ItemSearchingRepository;
import com.ecommerce.itemservice.repository.seller.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemSearchingService {
    @Autowired
    ItemRepository itemRepository;
    ItemMapper itemMapper = new ItemMapper();

    @Transactional(readOnly = true)
    public List<ItemListDTO> findItemListDTOsByName(String itemName) {
        String pattern = "%"+itemName+"%"; // e를 포함하는 패턴
        List<Item> items = itemRepository.findByItemNameLike(pattern).orElseThrow(() -> new IllegalArgumentException("해당하는 아이템이 없습니다 name : " + itemName));;

        System.out.println("Item 이름 검색");
        return items.stream().map(this::mapToItemListDTO).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public ItemDTO findItemDTOByItemNameAndSellerId(int sellerId, String itemName) {
        String pattern = "%"+itemName+"%"; // e를 포함하는 패턴
        Item item = itemRepository.findBySalesInfosSellerIdAndItemName(sellerId, pattern).orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다 id : " + sellerId));;
        SalesInfo salesInfo = item.getSalesInfos().get(0);
        return itemMapper.convertToItemDto(item, salesInfo);
    }
    @Transactional
    public List<ItemListDTO> findItemListDTOsByNameAndCat(String itemName,String CatName){ // 이름 ,카테 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        String patternName = "%"+itemName+"%"; // e를 포함하는 패턴

        List<Item> items = itemRepository.findByItemNameLikeAndCategory_CategoryName(patternName,CatName).orElseThrow(() -> new IllegalArgumentException("해당하는 아이템이 없습니다 id : " + itemName));
        return items.stream().map(this::mapToItemListDTO).collect(Collectors.toList());
    }
    @Transactional
    public List<ItemListDTO> findItemListDTOsByNameAndCatMin(String itemName,String CatName, int minPrice){ // 이름 카테, 최소 가격기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        String patternName = "%"+itemName+"%"; // e를 포함하는 패턴

        List<Item> items = itemRepository.findByItemNameLikeAndCategory_CategoryNameAndSalesInfosItemPriceGreaterThanEqual(patternName,CatName,minPrice).orElseThrow(() -> new IllegalArgumentException("해당하는 아이템이 없습니다 id : " + itemName));
        return items.stream().map(this::mapToItemListDTO).collect(Collectors.toList());
    }
    @Transactional
    public List<ItemListDTO> findItemListDTOsByNameAndCatMinMax(String itemName,String CatName,int minPrice, int maxPrice){ // 이름,카테,최대최소가격 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        String patternName = "%"+itemName+"%"; // e를 포함하는 패턴

        List<Item> items =  itemRepository.findItemsByItemNameLikeAndCategory_CategoryNameAndSalesInfosItemPriceBetween(patternName,CatName,minPrice,maxPrice).orElseThrow(() -> new IllegalArgumentException("해당하는 아이템이 없습니다 id : " + itemName));
        return items.stream().map(this::mapToItemListDTO).collect(Collectors.toList());
    }
    @Transactional

    public List<ItemListDTO> findItemListDTOsByCatMinMax(String CatName,int minPrice, int maxPrice){ // 카테,최대,최소 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        List<Item> items =  itemRepository.findByCategory_CategoryNameAndSalesInfosItemPriceBetween(CatName,minPrice,maxPrice).orElseThrow(() -> new IllegalArgumentException("해당하는 가격엔 상품이없습니다 : "));
        return items.stream().map(this::mapToItemListDTO).collect(Collectors.toList());

    }
    @Transactional
    public List<ItemListDTO> findItemListDTOsByCatMin(String CatName,int minPrice){ // 카테,최소가격 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");

        List<Item> items =  itemRepository.findByCategory_CategoryNameAndSalesInfosItemPriceGreaterThanEqual(CatName,minPrice).orElseThrow(() -> new IllegalArgumentException("해당하는 가격엔 상품이없습니다 : "));;;
        return items.stream().map(this::mapToItemListDTO).collect(Collectors.toList());


    }
    @Transactional
    public List<ItemListDTO> findItemListDTOsByCatMax(String CatName, int maxPrice){ // 카테, 최대가격 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");

        List<Item> items =  itemRepository.findByCategory_CategoryNameAndSalesInfosItemPriceLessThanEqual(CatName,maxPrice).orElseThrow(() -> new IllegalArgumentException("해당하는 가격엔 상품이없습니다 : "));;;
        return items.stream().map(this::mapToItemListDTO).collect(Collectors.toList());
    }
    public ItemListDTO mapToItemListDTO(Item item){
        return itemMapper.mapToItemListDTO(item);
    }

}
