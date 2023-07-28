package com.example.mini_project.controller;

import com.example.mini_project.auth.AuthenticationResponse;
import com.example.mini_project.dto.user.Request.LoginRequestDto;
import com.example.mini_project.dto.user.Request.RegisterRequest;
import com.example.mini_project.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/signUp")
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}