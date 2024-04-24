package com.ecommerce.memberservice.controller;

import com.ecommerce.memberservice.dto.CustomerDto;
import com.ecommerce.memberservice.entity.CustomerDetail;
import com.ecommerce.memberservice.entity.Member;
import com.ecommerce.memberservice.service.AuthenticationService;
import com.ecommerce.memberservice.service.CustomerDetailService;
import com.ecommerce.memberservice.service.MemberService;
import com.ecommerce.memberservice.vo.ChangePermissionRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationService authenticationService;

    // Only for Admin
    @GetMapping("")
    public ResponseEntity<List<Member>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Member> findCustomerById(@PathVariable int id)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping("/email/{email}")
    public CustomerDto findCustomerIdByEmail(@PathVariable String email, HttpServletRequest request)
            throws ChangeSetPersister.NotFoundException {
        return memberService.findCustomerIdByEmail(email);
    }

    @GetMapping("/customer-detail/{email}")
    public ResponseEntity<CustomerDetail> findCustomerDetailByEmail(@PathVariable String email)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(memberService.findCustomerDetailByEmail(email));
    }

    @PatchMapping("/grant-permission")
    public ResponseEntity<String> grantPermission
            (@RequestBody ChangePermissionRequest changePermissionRequest, Principal admin)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(authenticationService.grantPermission(changePermissionRequest, admin));
    }


}