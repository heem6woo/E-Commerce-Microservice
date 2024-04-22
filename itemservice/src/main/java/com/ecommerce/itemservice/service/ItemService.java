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
    public List<ItemDTO> findItemDTOsByName(String itemName){
        System.out.println("adad");
        return itemRepository.findItemsByNameLike(itemName);
    }
}
