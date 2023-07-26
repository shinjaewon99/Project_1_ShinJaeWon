package com.example.mini_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtTest {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Test
    void 시크릿키_존재_확인() {
        assertThat(secretKey).isNotNull();
    }
}
