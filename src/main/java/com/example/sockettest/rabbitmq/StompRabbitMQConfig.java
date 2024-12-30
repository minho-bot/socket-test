package com.example.sockettest.rabbitmq;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompRabbitMQConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 경로 단순화: RabbitMQ 메시지 브로커 설정
        config.enableStompBrokerRelay("/topic", "/queue") // 구독 경로 설정
                .setRelayHost("localhost")                // RabbitMQ 호스트
                .setRelayPort(61613)                      // STOMP 포트
                .setClientLogin("guest")                  // RabbitMQ 로그인
                .setClientPasscode("guest");              // RabbitMQ 패스코드

        // 클라이언트 메시지 발행 경로
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // STOMP 엔드포인트 설정
        registry.addEndpoint("/stomp/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // SockJS 사용
    }
}
