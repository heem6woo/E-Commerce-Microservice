package com.ecommerce.itemservice.service;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.dto.ItemValues;
import com.ecommerce.itemservice.dto.SalesValues;
import com.ecommerce.itemservice.entity.Category;
import com.ecommerce.itemservice.entity.Item;
import com.ecommerce.itemservice.entity.SalesInfo;
import com.ecommerce.itemservice.repository.seller.ItemRepository;
import com.ecommerce.itemservice.repository.SalesInfoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Item item = null;
        SalesInfo salesInfo = null;
        try {
            try {
                item = Item.builder()
                        .itemName(itemDTO.getItemValues().getItemName())
                        .itemDescription(itemDTO.getItemValues().getItemDescription())
                        .category(entityManager.getReference(Category.class, itemDTO.getItemValues().getCategory())) // You need to handle Category creation or lookup
                        .build();
                item = itemRepository.save(item);
            }finally {
                System.out.println("아이디" + item.getItemId());
                salesInfo = SalesInfo.builder()
                        .sellerId(itemDTO.getSalesValues().getSellerId())
                        .item(entityManager.getReference(Item.class, item.getItemId()))
                        .itemCount(itemDTO.getSalesValues().getItemCount())
                        .itemPrice(itemDTO.getSalesValues().getItemPrice())
                        .itemStatus(itemDTO.getSalesValues().getItemStatus())
                        .build();
                salesInfo = salesInfoRepository.save(salesInfo);
                entityManager.close();
            }
            // Create and save the Item entity from itemDTO
        } finally {
            return toItemDTO(item,salesInfo);
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
        // ItemDTO로부터 정보 추출
        ItemValues itemValues = itemDTO.getItemValues();
        SalesValues salesValues = itemDTO.getSalesValues();

        // 아이템 엔티티 조회 및 업데이트
        Item item = itemRepository.findById(itemValues.getItemId()).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setItemName(itemValues.getItemName());
        item.setItemDescription(itemValues.getItemDescription());
        // 여기서 필요에 따라 다른 아이템 속성 업데이트
        itemRepository.save(item);

        // 판매 정보 엔티티 조회 및 업데이트
        SalesInfo salesInfo = salesInfoRepository.findById(salesValues.getSalesId()).orElseThrow(() -> new RuntimeException("SalesInfo not found"));
        salesInfo.setSellerId(salesValues.getSellerId());
        salesInfo.setItemCount(salesValues.getItemCount());
        salesInfo.setItemPrice(salesValues.getItemPrice());
        salesInfo.setItemStatus(salesValues.getItemStatus());
        // 여기서 필요에 따라 다른 판매 정보 속성 업데이트
        salesInfoRepository.save(salesInfo);

        // 업데이트된 정보 반환
        return itemDTO;
    }

    @Transactional
    public Item patchItem(int itemId, Map<String,String> update) {
        System.out.println("fkfkfkfk");
        // apply specific changes from updates map to item
        Item item1 = entityManager.find(Item.class, itemId);
        String name = update.get("name");
        if(name != null){
            item1.setItemName(name);
            System.out.println("adadad");
        }
        String description = update.get("description");
        if(description != null){
            item1.setItemDescription(description);
        }
        String category = update.get("category");
        if(category != null){
            item1.setCategory(entityManager.getReference(Category.class, itemId));
        }
        entityManager.close();
        return itemRepository.save(item1);
    }

    @Transactional
    public void deleteItem(int itemId) {
        System.out.println("qwerasdf"+itemId);
        itemRepository.deleteById(itemId);
    }

    public ItemDTO toItemDTO(Item item,SalesInfo salesInfo) {
        if (item == null) {
            return null;
        }
        if(salesInfo == null){
            return null;
        }

        ItemValues itemValues = ItemValues.builder()
                .itemId(item.getItemId())
                .category(item.getCategory().getCategoryId()) // Assuming Category entity has an getId() method.
                .itemName(item.getItemName())
                .itemDescription(item.getItemDescription())
                .req_Dt(item.getRegDt())
                .build();

        SalesValues salesValues = SalesValues.builder()
                .salesId(salesInfo.getSalesInfoId()) // Assuming SalesInfo has a getSalesId() method.
                .sellerId(salesInfo.getSellerId()) // Assuming SalesInfo has a getSellerId() method.
                .itemCount(salesInfo.getItemCount()) // Assuming SalesInfo has a getItemCount() method.
                .itemPrice(salesInfo.getItemPrice()) // Assuming SalesInfo has a getItemPrice() method.
                .itemStatus(salesInfo.getItemStatus()) // Assuming SalesInfo has a getItemStatus() method.
                .build();
        return new ItemDTO(itemValues, salesValues);
    }
}