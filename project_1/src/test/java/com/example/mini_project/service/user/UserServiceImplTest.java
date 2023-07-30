package com.example.mini_project.service.user;

import com.example.mini_project.auth.AuthenticationResponse;
import com.example.mini_project.dto.ResponseDto;
import com.example.mini_project.dto.user.Request.LoginRequestDto;
import com.example.mini_project.dto.user.Request.RegisterRequest;
import com.example.mini_project.entity.UserEntity;
import com.example.mini_project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void before() {
        userRepository.deleteAll();
    }

    @Test
    void 회원가입_테스트() {
        // given
        RegisterRequest request = RegisterRequest.builder()
                .userId("shin")
                .password("1234")
                .phoneNumber("010-1234-5678")
                .email("shin@example.com")
                .city("seoul")
                .country("gangnam")
                .build();
        // when
        ResponseDto response = userService.register(request);
        UserEntity registeredUser = userRepository.findByUserId(request.getUserId()).orElse(null);

        // then
        assertThat(response.getMessage()).isEqualTo("회원가입이 완료되었습니다.");
        assertThat(registeredUser).isNotNull();
        assertThat(registeredUser.getUserId()).isEqualTo("shin");
    }

    @Test
    void 로그인_테스트() {
        String userId = "shin";
        String password = "1234";
        // given
        RegisterRequest registerRequest = RegisterRequest.builder()
                .userId(userId)
                .password(password)
                .phoneNumber("010-1234-5678")
                .email("shin@example.com")
                .city("seoul")
                .country("gangnam")
                .build();

        userService.register(registerRequest);

        // when
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setUserId(userId);
        loginRequest.setPassword(password);

        AuthenticationResponse response = userService.authenticate(loginRequest);


        // then
        assertThat(response).isNotNull(); // null이 아닌것은 토큰이 생성되었다는것
        assertThat(response.getToken()).isNotBlank(); // 공백이나 빈 문자열인지 검증

    }
}