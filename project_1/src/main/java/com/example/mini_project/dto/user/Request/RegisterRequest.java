package com.example.mini_project.dto.user.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private static final RegisterRequest EXISTING_USER_DTO = new RegisterRequest(); // 호출 할때마다 새로운 객체 생성을 막기위한 static 상수

    @NotBlank(message = "아이디는 필수 입니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 필수 입니다.")
    private String password;
    private String passwordCheck;
    private String phoneNumber;
    @Email
    private String email;
    private String city;
    private String country;

    private static RegisterRequest getUserCreateDto() {
        return EXISTING_USER_DTO;
    }
}