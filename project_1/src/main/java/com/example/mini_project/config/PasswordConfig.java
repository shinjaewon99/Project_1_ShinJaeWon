package com.example.mini_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // 설정파일이라는것을 알려주는 어노테이션
public class PasswordConfig {
    @Bean
    // PasswordEncoder :  Spring Security에서 지원하는 비밀번호를 단방향 암호화 시켜주는 인터페이스
    // 비밀번호는 평문 그대로 데이터베이스에 저장되면 안됨
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}