//package com.example.sockettest.stomp;
//
//
//import org.springframework.messaging.handler.annotation.*;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class StompController {
//
//    private final SimpMessageSendingOperations messagingTemplate;
//    private final ChatRoomService chatRoomService;
//
//    public StompController(SimpMessageSendingOperations messagingTemplate, ChatRoomService chatRoomService) {
//        this.messagingTemplate = messagingTemplate;
//        this.chatRoomService = chatRoomService;
//    }
//
//    // 입장 처리
//    @MessageMapping("/chat/{roomId}/enter")
//    public void enter(@DestinationVariable String roomId, @Payload ChatMessage message) {
//        // 입장 메시지 생성
//        message.setContent(message.getSender() + "님이 입장하셨습니다.");
//        message.setType("ENTER");
//
//        // 메시지를 캐시에 저장
//        chatRoomService.addMessage(roomId, message);
//
//        // 구독 중인 클라이언트들에게 메시지 전송
//        messagingTemplate.convertAndSend("/topic/chat/" + roomId, message);
//    }
//
//    // 메시지 처리
//    @MessageMapping("/chat/{roomId}/send")
//    public void sendMessage(@DestinationVariable String roomId, @Payload ChatMessage message) {
//        // 메시지 타입 설정
//        message.setType("CHAT");
//
//        // 메시지를 캐시에 저장
//        chatRoomService.addMessage(roomId, message);
//
//        // 구독 중인 클라이언트들에게 메시지 전송
//        messagingTemplate.convertAndSend("/topic/chat/" + roomId, message);
//    }
//
//    // 퇴장 처리
//    @MessageMapping("/chat/{roomId}/leave")
//    public void leave(@DestinationVariable String roomId, @Payload ChatMessage message) {
//        // 퇴장 메시지 생성
//        message.setContent(message.getSender() + "님이 퇴장하셨습니다.");
//        message.setType("LEAVE");
//
//        // 메시지를 캐시에 저장
//        chatRoomService.addMessage(roomId, message);
//
//        // 구독 중인 클라이언트들에게 메시지 전송
//        messagingTemplate.convertAndSend("/topic/chat/" + roomId, message);
//    }
//}
