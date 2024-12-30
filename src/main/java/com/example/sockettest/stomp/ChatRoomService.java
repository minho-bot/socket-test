package com.example.sockettest.stomp;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatRoomService {

    // 채팅방 목록 (메모리 저장)
    private final Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

    // 채팅방별 메시지 목록 (메모리 저장)
    private final Map<String, List<ChatMessage>> chatMessages = new ConcurrentHashMap<>();

    // 채팅방 생성
    public ChatRoom createRoom(String name) {
        String id = UUID.randomUUID().toString(); // 고유 ID 생성
        ChatRoom room = new ChatRoom(id, name);
        chatRooms.put(id, room);
        chatMessages.put(id, new ArrayList<>()); // 메시지 초기화
        return room;
    }

    // 채팅방 목록 조회
    public List<ChatRoom> findAllRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    // 특정 채팅방 조회
    public ChatRoom findRoomById(String id) {
        return chatRooms.get(id);
    }

    // 특정 채팅방의 메시지 조회
    public List<ChatMessage> getMessages(String roomId) {
        return chatMessages.getOrDefault(roomId, new ArrayList<>());
    }

    // 메시지 추가
    public void addMessage(String roomId, ChatMessage message) {
        chatMessages.get(roomId).add(message);
    }
}
