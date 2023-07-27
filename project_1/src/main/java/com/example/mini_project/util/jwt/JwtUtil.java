package com.example.mini_project.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component // @Component : 개발자가 직접 컨트롤이 가능한 클래스의 경우 사용, @Baen은 메소드에 사용
public class JwtUtil {
    private final Key signingKey;
    private final long tokenValidateTime = 1000 * 60 * 60l;

    public JwtUtil(
            @Value("${jwt.secretKey}")
            String jwtSecret
    ) {
        this.signingKey
                = Keys.hmacShaKeyFor(jwtSecret.getBytes()); // 비밀 키를 생성
    }

    // 주어진 UserDetails 객체 정보를 바탕으로 jwt 토큰 문자열 생성
    public String generateToken(UserDetails userDetails) {
        // Claims: jwt에 담기는 정보의 단위 (Map 객체), 사용자 정보와 유효기간을 포함하여 jwt 토큰 생성
        Claims jwtClaims = Jwts.claims()
                // 사용자 정보 등록
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(tokenValidateTime)));

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(signingKey)
                .compact();
    }
}