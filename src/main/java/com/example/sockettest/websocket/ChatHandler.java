package com.example.sockettest.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

public class ChatHandler extends TextWebSocketHandler {

    private final Map<WebSocketSession, String> userSessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 인터셉터에서 저장한 userId 가져오기
        Map<String, Object> attributes = session.getAttributes();
        String userId = (String) attributes.get("userId");

        // 세션과 userId 매핑
        userSessions.put(session, userId);
        System.out.println("새로운 연결: " + userId + " (세션 ID: " + session.getId() + ")");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userId = userSessions.get(session); // 세션에서 userId 가져오기
        System.out.println("수신 메시지 [" + userId + "]: " + message.getPayload());

        // 모든 클라이언트에게 브로드캐스트
        for (WebSocketSession s : userSessions.keySet()) {
            if (s.isOpen()) {
                s.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String userId = userSessions.remove(session); // 연결 종료 시 제거
        System.out.println("연결 종료: " + userId + " (세션 ID: " + session.getId() + ")");
    }

}

