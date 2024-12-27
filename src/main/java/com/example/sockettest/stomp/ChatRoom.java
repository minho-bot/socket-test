package com.example.sockettest.stomp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
    private String id;   // 채팅방 ID
    private String name; // 채팅방 이름

    public ChatRoom(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
