package com.ecommerce.reviewservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="item-service")
public interface ItemFeign {

    @GetMapping("/api/item-search/?item-name={:item-name}")
    Object retrieveItemId(@RequestParam(name = "item-name") String itemName);

}
