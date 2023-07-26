package com.example.mini_project;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class PasswordEncoderTest {

    PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordEncoderTest(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    void 패스워드_암호화_확인() {
        String plainPassword = "shinjaewon1999!@#";
        String encoderPassword = passwordEncoder.encode(plainPassword);

        log.info("평문 비밀번호 : {}", plainPassword);
        log.info("암호화 비밀번호 : {}", encoderPassword);

        assertNotEquals(plainPassword, encoderPassword);
        assertTrue(passwordEncoder.matches(plainPassword, encoderPassword));
    }
}
