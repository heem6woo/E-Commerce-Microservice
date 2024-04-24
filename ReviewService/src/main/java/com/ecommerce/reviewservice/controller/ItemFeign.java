package com.ecommerce.reviewservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="item-service")
public interface ItemFeign {

    @GetMapping("/api/items/{itemName}")
    Object retrieveItemId(@PathVariable String itemName);

}
