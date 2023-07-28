package com.example.mini_project.service.user;

import com.example.mini_project.auth.AuthenticationResponse;
import com.example.mini_project.config.JwtService;
import com.example.mini_project.dto.user.Request.LoginRequestDto;
import com.example.mini_project.dto.user.Request.RegisterRequest;
import com.example.mini_project.entity.UserEntity;
import com.example.mini_project.repository.UserRepository;
import com.example.mini_project.util.domain.Address;
import com.example.mini_project.util.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // 회원가입 메소드
    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        UserEntity user = UserEntity.builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .address(new Address(request.getCity(), request.getCountry()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }

    // 로그인 메소드
    @Override
    public AuthenticationResponse authenticate(LoginRequestDto request) {
        // 사용자를 인증
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserId(),
                        request.getPassword()
                )
        );

        UserEntity user = userRepository.findByUserId(request.getUserId()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }
}