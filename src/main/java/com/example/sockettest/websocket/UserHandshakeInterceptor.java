package com.example.sockettest.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class UserHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {

        // 쿼리 파라미터에서 userId 추출
        String query = request.getURI().getQuery();
        if (query != null && query.contains("userId=")) {
            String userId = query.split("userId=")[1]; // userId 추출
            attributes.put("userId", userId); // 세션 속성에 저장
        } else {
            attributes.put("userId", "unknown"); // 기본값 설정
        }
        return true; // 핸드셰이크 성공
    }

    @Override
    public void afterHandshake(org.springframework.http.server.ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
