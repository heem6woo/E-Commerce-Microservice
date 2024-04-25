package com.ecommerce.memberservice.controller;


import com.ecommerce.memberservice.service.AuthenticationService;
import com.ecommerce.memberservice.vo.AuthenticateRequest;
import com.ecommerce.memberservice.vo.AuthenticateResponse;
import com.ecommerce.memberservice.vo.ChangePasswordRequest;
import com.ecommerce.memberservice.vo.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authenticationService.register(request));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/access-token")
    public void reissueAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.reissueAccessToken(request, response);

    }

}
