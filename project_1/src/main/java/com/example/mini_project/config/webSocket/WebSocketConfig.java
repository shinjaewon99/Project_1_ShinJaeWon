package com.example.mini_project.config.webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketSecurity
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    // STOMP 엔드포인트를 등록하는 역할
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("chat"); // '/chat' 경로로 stomp 엔드포인트 등록
    }

    @Override
    // 메시지의 라우팅과 전달을 중개하는 역할
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/user/**");
        registry.setApplicationDestinationPrefixes("/app", "/topic");
    }
}
