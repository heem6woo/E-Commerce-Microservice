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
    String joinQuery = "SELECT new com.ecommerce.itemservice.dto.ItemDTO(" +
            "new com.ecommerce.itemservice.dto.ItemInfo(i.itemId, c.categoryId, i.itemName, i.regDt), " +
            "new com.ecommerce.itemservice.dto.SalesInfoDTO(s.salesInfoId, s.sellerId, s.itemCount, s.itemPrice, s.itemStatus)) " +
            "FROM Item i " +
            "JOIN i.category c " +
            "JOIN i.salesInfo s ";
    String whereName = "WHERE i.itemName LIKE concat('%', :name, '%')";
    
    @Query( joinQuery+
            whereName)
    List<ItemDTO> findItemsByNameLike(@Param("name") String name);
    @Query(joinQuery +
            whereName +
            "AND c.categoryId = :catId "
    )
    List<ItemDTO> findItemsByNameLikeAndSameCat(@Param("name") String name,@Param("catId") short catId );

    @Query(joinQuery+
            whereName +
            "AND c.categoryId = :catId "+
            "AND s.itemPrice >= :minPrice"
    )
    List<ItemDTO> findItemsByNameLikeAndSameCatMin(@Param("name") String name,@Param("catId") short catId ,@Param("minPrice")int minPrice);

    @Query(joinQuery+
            whereName +
            "AND c.categoryId = :catId "+
            "AND s.itemPrice >= :minPrice "+
            "AND s.itemPrice <= :maxPrice"
    )
    List<ItemDTO> findItemsByNameLikeAndSameCatMinMax(@Param("name") String name,@Param("catId") short catId,@Param("minPrice")int minPrice,@Param("maxPrice")int maxPrice );
    @Query(joinQuery+
            whereName+
            "AND s.itemPrice >= :minPrice "+
            "AND s.itemPrice <= :maxPrice"
    )
    List<ItemDTO> findItemsByCatMinMax(@Param("catId") short catId,@Param("minPrice")int minPrice,@Param("maxPrice")int maxPrice );
    @Query(joinQuery+
            "WHERE c.categoryId = :catId "+
            "AND s.itemPrice <= :maxPrice"
    )
    List<ItemDTO> findItemsByCatMax(@Param("catId") short catId,@Param("maxPrice")int maxPrice);
    @Query(joinQuery+
            "WHERE c.categoryId = :catId "+
            "AND s.itemPrice >= :minPrice"
    )
    List<ItemDTO> findItemsByCatMin(@Param("catId") short catId,@Param("minPrice")int minPrice );

}
