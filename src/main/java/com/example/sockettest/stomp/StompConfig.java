package com.example.sockettest.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지 브로커 설정
        config.enableSimpleBroker("/topic"); // 구독 경로 (브로드캐스트용)
        config.setApplicationDestinationPrefixes("/app"); // 메시지 발행 경로
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트 WebSocket 연결 엔드포인트 설정
        registry.addEndpoint("/stomp/chat") // 연결 주소: ws://localhost:8080/stomp
                .setAllowedOriginPatterns("*") // 모든 도메인 허용
                .withSockJS(); // SockJS 사용
    }
}