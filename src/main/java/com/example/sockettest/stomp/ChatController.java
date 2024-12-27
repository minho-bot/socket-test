package com.example.sockettest.stomp;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomService chatRoomService;

    public ChatController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    // 채팅방 생성
    @PostMapping("/room")
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomService.createRoom(name);
    }

    // 채팅방 목록 조회
    @GetMapping("/rooms")
    public List<ChatRoom> getRooms() {
        return chatRoomService.findAllRooms();
    }

    // 특정 채팅방 메시지 조회
    @GetMapping("/room/{roomId}/messages")
    public List<ChatMessage> getMessages(@PathVariable String roomId) {
        return chatRoomService.getMessages(roomId);
    }

    // 입장 처리
    @MessageMapping("/chat/{roomId}/enter")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessage enter(@Payload ChatMessage message, @DestinationVariable String roomId) {
        message.setContent(message.getSender() + "님이 입장하셨습니다.");
        message.setType("ENTER");
        chatRoomService.addMessage(roomId, message); // 메시지 저장
        return message;
    }

    // 메시지 전송
    @MessageMapping("/chat/{roomId}/send")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessage sendMessage(@Payload ChatMessage message, @DestinationVariable String roomId) {
        System.out.println("message = " + message.toString());
        message.setType("CHAT");
        chatRoomService.addMessage(roomId, message); // 메시지 저장
        return message;
    }

    // 퇴장 처리
    @MessageMapping("/chat/{roomId}/leave")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessage leave(@Payload ChatMessage message, @DestinationVariable String roomId) {
        message.setContent(message.getSender() + "님이 퇴장하셨습니다.");
        message.setType("LEAVE");
        chatRoomService.addMessage(roomId, message); // 메시지 저장
        return message;
    }
}
