package com.example.sockettest.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

    private final SimpMessageSendingOperations messagingTemplate;

    public StompController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 입장 처리
    @MessageMapping("/enter")
    @SendTo("/topic/chat")
    public String enter(@Payload String userId) {
        return "[알림] " + userId + "님이 입장했습니다.";
    }

    // 메시지 처리
    @MessageMapping("/message")
    @SendTo("/topic/chat")
    public String message(@Payload ChatMessage message) {
        return message.getUserId() + ": " + message.getContent();
    }

    // 퇴장 처리
    @MessageMapping("/leave")
    @SendTo("/topic/chat")
    public String leave(@Payload String userId) {
        return "[알림] " + userId + "님이 퇴장했습니다.";
    }
}
