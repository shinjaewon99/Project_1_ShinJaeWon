package com.example.mini_project.dto.user.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "아이디는 필수 입니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 필수 입니다.")
    private String password;
}
