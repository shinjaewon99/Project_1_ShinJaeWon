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
}