package com.example.mini_project.config;

import com.example.mini_project.util.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // 설정파일이라는것을 알려주는 어노테이션
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                /**
                 csrf  비활성화 : non-browser clients만을 위한 서비스라면 csrf를 비활성화 해도 좋다.
                 cors 비활성화 : 다른곳의 AJAX 요청을 허용합니다. (필요한 경우에만 비활성화 한다.)
                 사용 : .cors(AbstractHttpConfigurer::disable)
                 **/
                .csrf()
                .disable()
                // 요청에 대한 보안 설정
                .authorizeHttpRequests()
                .requestMatchers("/user/register")
                .permitAll()
                .requestMatchers("/user/signUp")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "items/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // 세션관리 설정
                // STATELESS 즉 무상태로 설정하는 것이 일반적으로 jwt 인증을 사용하는데 사용
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
