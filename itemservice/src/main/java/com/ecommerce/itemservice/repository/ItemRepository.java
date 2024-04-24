package com.ecommerce.itemservice.repository;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.entity.Category;
import com.ecommerce.itemservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    // Custom query to update an item
    @Transactional
    @Modifying
    @Query("UPDATE Item i SET i.itemName = :itemName, i.itemDescription = :itemDescription, i.category = :category WHERE i.itemId = :itemId")
    ItemDTO updateItem(@Param("itemId") Long itemId,
                       @Param("itemName") String itemName,
                       @Param("itemDescription") String itemDescription,
                       @Param("category") Category category);

}