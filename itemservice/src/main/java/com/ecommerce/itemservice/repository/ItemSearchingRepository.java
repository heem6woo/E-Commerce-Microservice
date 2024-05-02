package com.ecommerce.itemservice.repository;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.dto.SalesValues;
import com.ecommerce.itemservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemSearchingRepository extends JpaRepository<Item, Integer> {
    String ItemDTOjoinQuery = "SELECT new com.ecommerce.itemservice.dto.ItemDTO(\n" +
            "  new com.ecommerce.itemservice.dto.ItemValues(i.itemId, c.categoryId, i.itemName, i.itemDescription, i.regDt),\n" +
            "  new com.ecommerce.itemservice.dto.SalesValues(s.salesInfoId, s.sellerId, i.itemId, s.itemCount, s.itemPrice, s.itemStatus)\n" +
            ")\n" +
            "FROM Item i\n" +
            "JOIN i.category c\n" +
            "JOIN i.salesInfos s\n";
    String whereItemName = "WHERE i.itemName LIKE concat('%', :name, '%') ";


    @Query( ItemDTOjoinQuery+
            whereItemName)
    List<ItemDTO> findItemsByNameLike(@Param("name") String name);
    @Query(ItemDTOjoinQuery +
            whereItemName +
            "AND c.categoryId = :catId "
    )
    List<ItemDTO> findItemsByNameLikeAndSameCat(@Param("name") String name,@Param("catId") short catId );

    @Query(ItemDTOjoinQuery +
            whereItemName +
            "AND s.salesInfoId = :sellerId " +
            "LIMT 1"
    )
    ItemDTO findItemsBysellerIdSalesInfos(@Param("sellerId") int sellerId, @Param("name") String name );

    @Query(ItemDTOjoinQuery+
            whereItemName +
            "AND c.categoryId = :catId "+
            "AND s.itemPrice >= :minPrice"
    )
    List<ItemDTO> findItemsByNameLikeAndSameCatMin(@Param("name") String name,@Param("catId") short catId ,@Param("minPrice")int minPrice);

    @Query(ItemDTOjoinQuery+
            whereItemName+
            "AND c.categoryId = :catId "+
            "AND s.itemPrice >= :minPrice "+
            "AND s.itemPrice <= :maxPrice"
    )
    List<ItemDTO> findItemsByNameLikeAndSameCatMinMax(@Param("name") String name,@Param("catId") short catId,@Param("minPrice")int minPrice,@Param("maxPrice")int maxPrice );
    @Query(ItemDTOjoinQuery+
            "WHERE c.categoryId = :catId "+
            "AND s.itemPrice >= :minPrice "+
            "AND s.itemPrice <= :maxPrice"
    )
    List<ItemDTO> findItemsByCatMinMax(@Param("catId") short catId,@Param("minPrice")int minPrice,@Param("maxPrice")int maxPrice );
    @Query(ItemDTOjoinQuery+
            "WHERE c.categoryId = :catId "+
            "AND s.itemPrice <= :maxPrice"
    )
    List<ItemDTO> findItemsByCatMax(@Param("catId") short catId,@Param("maxPrice")int maxPrice);
    @Query(ItemDTOjoinQuery+
            "WHERE c.categoryId = :catId "+
            "AND s.itemPrice >= :minPrice"
    )
    List<ItemDTO> findItemsByCatMin(@Param("catId") short catId,@Param("minPrice")int minPrice );

}
