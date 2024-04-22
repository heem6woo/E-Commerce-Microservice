package com.ecommerce.itemservice.service;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    public List<ItemDTO> findItemDTOsByName(String itemName){ // 이름 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemRepository.findItemsByNameLike(itemName);
    }
    public List<ItemDTO> findItemDTOsByNameAndCat(String itemName,short itemCat){ // 이름 ,카테 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemRepository.findItemsByNameLikeAndSameCat(itemName,itemCat);
    }
    public List<ItemDTO> findItemDTOsByNameAndCatMin(String itemName,short itemCat, int minPrice){ // 이름 카테, 최소 가격기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemRepository.findItemsByNameLikeAndSameCatMin(itemName,itemCat,minPrice);
    }
    public List<ItemDTO> findItemDTOsByNameAndCatMinMax(String itemName,short itemCat,int minPrice, int maxPrice){ // 이름,카테,최대최소가격 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemRepository.findItemsByNameLikeAndSameCatMinMax(itemName,itemCat,minPrice,maxPrice);
    }

    public List<ItemDTO> findItemDTOsByCatMinMax(short itemCat,int minPrice, int maxPrice){ // 카테,최대,최소 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemRepository.findItemsByCatMinMax(itemCat,minPrice,maxPrice);
    }
    public List<ItemDTO> findItemDTOsByCatMin(short itemCat,int minPrice){ // 카테,최소가격 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemRepository.findItemsByCatMin(itemCat,minPrice);
    }
    public List<ItemDTO> findItemDTOsByCatMax(short itemCat, int maxPrice){ // 카테, 최대가격 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemRepository.findItemsByCatMax(itemCat,maxPrice);
    }
}
