package com.ecommerce.itemservice.controller;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.entity.Item;
import com.ecommerce.itemservice.service.ItemService;
import com.ecommerce.itemservice.service.ItemStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/items/*")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemStockService itemStockService;

    // Create a single item
    @PostMapping("")
    public ItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        return itemService.createItem(itemDTO);
    }

    // Create a list of items
    @PostMapping("list")
    public List<ItemDTO> createItems(@RequestBody List<ItemDTO> itemDTOs) {
        return itemService.createItems(itemDTOs);
    }
    @PostMapping("decrease")
    public boolean decreaseItems(@RequestBody ItemDTO itemDTO) {
        return itemStockService.decrease(itemDTO.getItemValues().getItemId(),itemDTO.getSalesValues().getSellerId(),itemDTO.getSalesValues().getItemCount());
    }
    @PostMapping("rollback")
    public boolean rollbackItems(@RequestBody ItemDTO itemDTO) {
        return itemStockService.rollBack(itemDTO.getItemValues().getItemId(),itemDTO.getSalesValues().getSellerId(),itemDTO.getSalesValues().getItemCount());
    }

    // Update a specific item
    @PutMapping("{itemId}")
    public ItemDTO updateItem(@PathVariable("itemId") int itemId,@RequestBody ItemDTO itemDTO) {
        return itemService.updateItem(itemDTO);
    }

    // Patch a specific item
    @PatchMapping("{itemId}")
    public Item patchItem(@PathVariable("itemId") int itemId, @RequestBody Map<String, String> updates) {
        return itemService.patchItem(itemId, updates);
    }

    // Delete a specific item
    @DeleteMapping("{itemId}")
    public void deleteItem(@PathVariable("itemId") int itemId) {
        itemService.deleteItem(itemId);
    }
}