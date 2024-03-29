package com.example.ChatApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessagePojo sendMessage(@Payload ChatMessagePojo chatMessagePojo){
        return chatMessagePojo;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessagePojo addUser(@Payload ChatMessagePojo chatMessagePojo, SimpMessageHeaderAccessor headerAccessor){
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessagePojo.getSender());
        return chatMessagePojo;
    }
}
