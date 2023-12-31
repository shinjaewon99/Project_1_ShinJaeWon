package com.example.mini_project.service.user;

import com.example.mini_project.auth.AuthenticationResponse;
import com.example.mini_project.config.security.JwtService;
import com.example.mini_project.dto.ResponseDto;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // 회원가입 메소드
    @Transactional
    @Override
    public ResponseDto register(RegisterRequest request) {
        String password = request.getPassword();
        String passwordCheck = request.getPasswordCheck();

        validatePassword(password, passwordCheck); // 비밀 번호 검증

        UserEntity user = UserEntity.builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(password))
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .address(new Address(request.getCity(), request.getCountry()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("회원가입이 완료되었습니다.");
        return responseDto;

    }

    // 로그인 메소드
    @Transactional
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

    // 비밀번호와 비밀번호 체크 검증 메소드
    private static void validatePassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new IllegalArgumentException("password와 passwordCheck가 일치하지 않습니다.");
        }
    }

}