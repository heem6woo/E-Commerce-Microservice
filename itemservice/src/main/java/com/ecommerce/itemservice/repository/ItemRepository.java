package com.ecommerce.itemservice.repository;

import com.ecommerce.itemservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    // Custom query to update an item
    Optional<List<Item>> findByItemNameLike(String itemName);

    Optional<Item> findBySalesInfosSellerIdAndItemName(int sellerId, String itemName);

    Optional<List<Item>> findByItemNameLikeAndCategory_CategoryName(
            String itemName, String categoryName);
    Optional<List<Item>> findByItemNameLikeAndCategory_CategoryNameAndSalesInfosItemPriceGreaterThanEqual(
            String itemName, String categoryName, double minPrice);

    Optional<List<Item>> findItemsByItemNameLikeAndCategory_CategoryNameAndSalesInfosItemPriceBetween(
            String itemName, String categoryName, double minPrice, double maxPrice);
    Optional<List<Item>> findByCategory_CategoryNameAndSalesInfosItemPriceGreaterThanEqual(
            String categoryName, double minPrice);
    Optional<List<Item>> findByCategory_CategoryNameAndSalesInfosItemPriceLessThanEqual(
            String categoryName, double maxPrice);
    Optional<List<Item>> findByCategory_CategoryNameAndSalesInfosItemPriceBetween(
            String categoryName, double minPrice, double maxPrice);

}
