package com.example.sockettest.stomp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String sender;   // 보낸 사람
    private String content;  // 메시지 내용
    private String type;     // 메시지 타입 (입장, 채팅, 퇴장)

    public ChatMessage(String sender, String content, String type) {
        this.sender = sender;
        this.content = content;
        this.type = type;
    }
}
