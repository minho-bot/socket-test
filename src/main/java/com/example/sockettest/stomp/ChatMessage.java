package com.example.sockettest.stomp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String userId;
    private String content;
}
