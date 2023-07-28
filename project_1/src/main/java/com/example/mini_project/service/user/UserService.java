package com.example.mini_project.service.user;

import com.example.mini_project.auth.AuthenticationResponse;
import com.example.mini_project.dto.user.Request.LoginRequestDto;
import com.example.mini_project.dto.user.Request.RegisterRequest;

public interface UserService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(LoginRequestDto request);
}
