package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="member-service"  )
public interface MemberFeign {

    @GetMapping("/api/members/email/{memberEamil}")
    CustomerDto retrieveCustomerId(@PathVariable String memberEamil);

}

