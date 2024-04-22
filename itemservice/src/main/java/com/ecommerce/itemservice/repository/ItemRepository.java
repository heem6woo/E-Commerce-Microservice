package com.ecommerce.itemservice.repository;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.dto.ItemInfo;
import com.ecommerce.itemservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT new com.ecommerce.itemservice.dto.ItemDTO(" +
            "new com.ecommerce.itemservice.dto.ItemInfo(i.itemId, c.categoryId, i.itemName, i.regDt), " +
            "new com.ecommerce.itemservice.dto.SalesInfoDTO(s.salesInfoId, s.sellerId, s.itemCount, s.itemPrice, s.itemStatus)) " +
            "FROM Item i " +
            "JOIN i.category c " +
            "JOIN i.salesInfo s " +
            "WHERE i.itemName LIKE concat('%', :name, '%')")
    List<ItemDTO> findItemsByNameLike(@Param("name") String name);

}
