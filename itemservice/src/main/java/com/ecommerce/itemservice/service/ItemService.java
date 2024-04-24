package com.ecommerce.itemservice.service;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.entity.Category;
import com.ecommerce.itemservice.entity.Item;
import com.ecommerce.itemservice.entity.SalesInfo;
import com.ecommerce.itemservice.repository.ItemRepository;
import com.ecommerce.itemservice.repository.SalesInfoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarException;

@Service
public class ItemService {
    @Autowired
    private EntityManager entityManager;  // EntityManager 주입
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SalesInfoRepository salesInfoRepository;

    @Transactional
    public ItemDTO createItem(ItemDTO itemDTO) {
        try {
            SalesInfo salesInfo = SalesInfo.builder()
                    .sellerId(itemDTO.getSalesValues().getSellerId())
                    .itemCount(itemDTO.getSalesValues().getItemCount())
                    .itemPrice(itemDTO.getSalesValues().getItemPrice())
                    .itemStatus(itemDTO.getSalesValues().getItemStatus())
                    .build();
            salesInfo = salesInfoRepository.save(salesInfo);

            // Create and save the Item entity from itemDTO
            Item item = Item.builder()
                    .itemName(itemDTO.getItemValues().getItemName())
                    .itemDescription(itemDTO.getItemValues().getItemDescription())
                    .regDt(itemDTO.getItemValues().getReq_Dt())
                    .category(entityManager.getReference(Category.class, itemDTO.getCategoryId())) // You need to handle Category creation or lookup
                    .salesInfo(salesInfo)
                    .build();
            itemRepository.save(item);

        } finally {
            return itemDTO;
        }
        // Create and save the SalesInfo entity from itemDTO
       }

    @Transactional
    public List<ItemDTO> createItems(List<ItemDTO> itemDTOs) {
        List<ItemDTO> rItemDTOs = new ArrayList<>();
        for (ItemDTO itemDTO: itemDTOs ) {
           rItemDTOs.add(createItem(itemDTO));
        }
        return rItemDTOs;
    }

    @Transactional
    public ItemDTO updateItem(ItemDTO itemDTO) {
        // apply changes to item and its associated salesInfo
        return itemRepository.updateItem(itemDTO.getItemValues().getItemId(),
                itemDTO.getItemValues().getItemName(),
                itemDTO.getItemValues().getItemDescription(),
                entityManager.getReference(Category.class, itemDTO.getCategoryId()));
    }

    @Transactional
    public Item patchItem(int itemId, Map<String,String> update) {
        // apply specific changes from updates map to item
        Item item1 = entityManager.find(Item.class, itemId);
        String name = update.get("name");
        if(name != null){
            item1.setItemName(name);
        }
        String description = update.get("description");
        if(description != null){
            item1.setItemDescription(description);
        }
        String category = update.get("category");
        if(category != null){
            item1.setCategory(entityManager.getReference(Category.class, itemId));
        }
        return item1;
    }

    @Transactional
    public void deleteItem(int itemId) {
        Item item = entityManager.getReference(Item.class, itemId);
        itemRepository.delete(item);
    }
}