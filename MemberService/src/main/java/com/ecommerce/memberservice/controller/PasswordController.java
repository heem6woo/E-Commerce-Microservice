package com.ecommerce.memberservice.controller;

import com.ecommerce.memberservice.service.AuthenticationService;
import com.ecommerce.memberservice.vo.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PasswordController {

    private final AuthenticationService authenticationService;

    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        return ResponseEntity.ok(authenticationService.changePassword(request, connectedUser));
    }

}
