package com.example.sockettest.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatHandler extends TextWebSocketHandler {

    // 연결된 클라이언트를 저장하는 리스트
    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        System.out.println("새로운 연결: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("수신 메시지: " + message.getPayload());
        // 모든 연결된 클라이언트에게 메시지 브로드캐스트
        for (WebSocketSession s : sessions) {
            if (s.isOpen()) {
                s.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.println("연결 종료: " + session.getId());
    }
}

