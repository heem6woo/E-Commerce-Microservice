package com.ecommerce.itemservice.controller;

import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.entity.Item;
import com.ecommerce.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Create a single item
    @PostMapping("/")
    public ItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        return itemService.createItem(itemDTO);
    }

    // Create a list of items
    @PostMapping("/")
    public List<ItemDTO> createItems(@RequestBody List<ItemDTO> itemDTOs) {
        return itemService.createItems(itemDTOs);
    }

    // Update a specific item
    @PutMapping("/")
    public ItemDTO updateItem(@RequestBody ItemDTO itemDTO) {
        return itemService.updateItem(itemDTO);
    }

    // Patch a specific item
    @PatchMapping("/")
    public Item patchItem(@RequestBody ItemDTO item, @RequestBody Integer index) {
        return itemService.patchItem(item, index);
    }

    // Delete a specific item
    @DeleteMapping("/")
    public void deleteItem(@PathVariable int itemId) {
        itemService.deleteItem(itemId);
    }
}