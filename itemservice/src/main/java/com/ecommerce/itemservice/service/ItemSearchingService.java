package com.ecommerce.itemservice.service;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.dto.SalesValues;
import com.ecommerce.itemservice.entity.SalesInfo;
import com.ecommerce.itemservice.repository.ItemSearchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ItemSearchingService {
    @Autowired
    ItemSearchingRepository itemSearchingRepository;

    @Transactional
    public List<ItemDTO> findItemDTOsByName(String itemName){ // 이름 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemSearchingRepository.findItemsByNameLike(itemName);
    }
    @Transactional
    public ItemDTO findByIdSalesValues(int sellerId,String itemName ){
        return itemSearchingRepository.findItemsBysellerIdSalesInfos(sellerId,itemName);
    }
    @Transactional
    public List<ItemDTO> findItemDTOsByNameAndCat(String itemName,short itemCat){ // 이름 ,카테 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemSearchingRepository.findItemsByNameLikeAndSameCat(itemName,itemCat);
    }
    @Transactional
    public List<ItemDTO> findItemDTOsByNameAndCatMin(String itemName,short itemCat, int minPrice){ // 이름 카테, 최소 가격기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemSearchingRepository.findItemsByNameLikeAndSameCatMin(itemName,itemCat,minPrice);
    }
    @Transactional
    public List<ItemDTO> findItemDTOsByNameAndCatMinMax(String itemName,short itemCat,int minPrice, int maxPrice){ // 이름,카테,최대최소가격 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemSearchingRepository.findItemsByNameLikeAndSameCatMinMax(itemName,itemCat,minPrice,maxPrice);
    }
    @Transactional

    public List<ItemDTO> findItemDTOsByCatMinMax(short itemCat,int minPrice, int maxPrice){ // 카테,최대,최소 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemSearchingRepository.findItemsByCatMinMax(itemCat,minPrice,maxPrice);
    }
    @Transactional
    public List<ItemDTO> findItemDTOsByCatMin(short itemCat,int minPrice){ // 카테,최소가격 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemSearchingRepository.findItemsByCatMin(itemCat,minPrice);
    }
    @Transactional
    public List<ItemDTO> findItemDTOsByCatMax(short itemCat, int maxPrice){ // 카테, 최대가격 기준 검색이후 itemDTO 리스트반환
        System.out.println("adad");
        return itemSearchingRepository.findItemsByCatMax(itemCat,maxPrice);
    }


}
