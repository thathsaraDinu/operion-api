package com.dinoryn.worksphere.controller;

import com.dinoryn.worksphere.dto.LoginRequest;
import com.dinoryn.worksphere.dto.LoginResponse;
import com.dinoryn.worksphere.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ){

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}